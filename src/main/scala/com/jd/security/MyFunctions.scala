package com.jd.security

import net.minidev.json.JSONObject
import net.minidev.json.parser.JSONParser

/**
  * Created by zhanghuayan on 2017/4/6.
  */
object MyFunctions {

  def func1(str: String): (MyValue, Long) = {

    var ans = new MyValue()

    val jsonParser = new JSONParser()
    val jsonObj: JSONObject = jsonParser.parse(str).asInstanceOf[JSONObject]

    val hostIp = jsonObj.getAsString("host")
    val remoteIp = jsonObj.getAsString("remote")
    val callTimes = jsonObj.getAsString("callTimes").toLong

    ans.hostIp = hostIp
    ans.remoteIp = remoteIp

    // 返回类型(MyValue, Int)
    (ans, callTimes)
  }
}
