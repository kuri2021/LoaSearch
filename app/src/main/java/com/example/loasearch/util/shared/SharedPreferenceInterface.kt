package com.example.loasearch.util.shared

interface SharedPreferenceInterface {
    fun saveIdPw(id:String,pw:String)
    fun saveKey(key:String)
    fun saveType(type:String)
    fun getId():String
    fun getPw():String
    fun getType():String
    fun getKey():String
    fun deleteData()
}