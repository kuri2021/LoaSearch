package com.kuri2024.loasearch.util.connet
//
//import android.util.Base64
//import android.util.Log
//import com.example.loasearch.signup.UserData
//import com.google.gson.Gson
//import java.math.BigInteger
//import java.security.MessageDigest
//import javax.crypto.Cipher
//import javax.crypto.spec.IvParameterSpec
//import javax.crypto.spec.SecretKeySpec
//
//class AES256 {
//    companion object {
//        const val key = "asdfasdfasdfasdfasdfasdfsdferver"
//        var iv = byteArrayOf(
//            0x01,
//            0x02,
//            0x03,
//            0x04,
//            0x05,
//            0x06,
//            0x07,
//            0x08,
//            0x09,
//            0x10,
//            0x11,
//            0x12,
//            0x13,
//            0x14,
//            0x15,
//            0x16
//        )
//    }
//
//    // 사용자 지정 키로 AES256 암호화
//    @Throws(java.lang.Exception::class)
//    fun encByKey(key: String, value: String): String {
//        return encByKey(key.toByteArray(), value.toByteArray())
//    }
//
//    // 사용자 지정 키로 AES256 복호화
//    @Throws(java.lang.Exception::class)
//    fun encByKey(key: ByteArray?, value: ByteArray?): String {
//        val secretKeySpec = SecretKeySpec(key, "AES")
//        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, IvParameterSpec(iv))
//        val randomKey = cipher.doFinal(value)
//        return Base64.encodeToString(randomKey, 0)
//    }
//
//    // 사용자 지정 키로 AES256 복호화
//    @Throws(java.lang.Exception::class)
//    fun decByKey(key: String, plainText: String?): String {
//        return decByKey(key.toByteArray(), Base64.decode(plainText, 0))
//    }
//
//    // 사용자 지정 키로 AES256 복호화
//    @Throws(java.lang.Exception::class)
//    fun decByKey(key: ByteArray?, encText: ByteArray?): String {
//        val secretKeySpec = SecretKeySpec(key, "AES")
//        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
//        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, IvParameterSpec(iv))
//        val secureKey = cipher.doFinal(encText)
//        return String(secureKey)
//    }
//
//
////    fun encByStr(str:String):String{
////        try {
////            val md: MessageDigest = MessageDigest.getInstance("SHA-512")
////            val messageDigest = md.digest(str.toByteArray())
////
////            val no = BigInteger(1, messageDigest)
////
////            var hashtext: String = no.toString(16)
////
////            while (hashtext.length < 32) {
////                hashtext = "0$hashtext"
////            }
////
////            return hashtext
////
////        } catch (e: Exception) {
////            Log.e("Exception", "Exception : ${e.message}")
////        }
////        return ""
////    }
//
////    fun encByJson(data: UserData): String {
////        try {
////            val strJson = Gson().toJson(data)
////            val iv = IvParameterSpec(iv)
////            val key = SecretKeySpec(key.toByteArray(), "AES")
////
////            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
////            cipher.init(Cipher.ENCRYPT_MODE, key,iv)
////
////            val crypted = cipher.doFinal(strJson.toByteArray())
////            val encodedByte = Base64.encode(crypted, Base64.DEFAULT)
////
////            return String(encodedByte)
////
////        } catch (e: Exception) {
////            Log.e("Exception", "Exception : ${e.message}")
////        }
////        return ""
////    }
//
////    fun decByKey(str: String): String {
////        val iv = IvParameterSpec(iv)
////        val key = SecretKeySpec(key.toByteArray(), "AES")
////
////        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
////        cipher.init(Cipher.DECRYPT_MODE, key, iv)
////
////        val decodedByte = Base64.decode(str, Base64.DEFAULT)
////        val byteResult = cipher.doFinal(decodedByte)
////
////        return String(byteResult)
////    }
//}