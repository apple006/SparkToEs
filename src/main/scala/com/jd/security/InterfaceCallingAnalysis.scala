package com.jd.security

import java.io.PrintWriter

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.elasticsearch.spark._
import scala.collection.JavaConversions._

/**
  * Created by zhanghuayan on 2017/4/6.
  */
object InterfaceCallingAnalysis {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("InterfaceCallingAnalysis")
    conf.set("es.index.auto.create", "true")
    conf.set("es.nodes", "172.20.174.149")
    conf.set("es.port", "30000")


    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(60))

    val lines = ssc.socketTextStream("172.20.79.189", 9999)
    val pairs = lines.map(MyFunctions.func1)

    val result = pairs.reduceByKey((a, b) => a + b)

    result.foreachRDD(rdd => {
      rdd.map(line => {
        val time = (System.currentTimeMillis() / 60000) * 60
        Map("hostIp" -> line._1.hostIp,
          "remoteIp" -> line._1.remoteIp,
          "callTimes" -> line._2.toLong,
          "time" -> time)
      }).saveToEs("jsf_node_speed/infoType")
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
