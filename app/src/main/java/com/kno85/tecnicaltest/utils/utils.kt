package com.kno85.tecnicaltest.utils

import com.kno85.tecnicaltest.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest

fun getHash(ts:String):String{
    val input= ts.plus(BuildConfig.PRIVATE_API_CLIENT_ID).plus(BuildConfig.API_CLIENT_ID)
    return md5(input)
}
private fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}