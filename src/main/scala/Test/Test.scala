package Test

import java.util

import net.minidev.json.JSONObject
import net.minidev.json.parser.JSONParser

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.util.parsing.json.JSON

/**
  * Created by zhanghuayan on 2017/3/30.
  */


object Test {

  def main(args: Array[String]): Unit = {

    val str2 = "{\"name\":\"jeemy\",\"age\":25,\"phone\":\"18810919225\"}"

    val jsonParser = new JSONParser()

    val jsonObj: JSONObject = jsonParser.parse(str2).asInstanceOf[JSONObject]


    val name = jsonObj.get("name").toString
    println(name)

    val jsonKey = jsonObj.keySet()
    val iter = jsonKey.iterator

    while (iter.hasNext) {
      val instance = iter.next()
      val value = jsonObj.get(instance).toString
      println("key: " + instance + " value:" + value)
    }

  }
}