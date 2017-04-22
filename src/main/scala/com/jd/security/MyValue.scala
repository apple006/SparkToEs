package com.jd.security

/**
  * Created by zhanghuayan on 2017/4/6.
  */
class MyValue extends Serializable {

  var hostIp = ""
  var remoteIp = ""

  override def toString: String = {
    "(" + this.hostIp + " " + this.remoteIp + ")"
  }

}
