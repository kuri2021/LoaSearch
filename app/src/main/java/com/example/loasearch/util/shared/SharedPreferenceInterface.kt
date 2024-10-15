package com.example.loasearch.util.shared

interface SharedPreferenceInterface {
    fun saveIdPw(id:String,pw:String)
    fun saveApiKey(api:String)
    fun getId():String
    fun getPw():String
    fun getApiKey():String
    fun deleteData()
}