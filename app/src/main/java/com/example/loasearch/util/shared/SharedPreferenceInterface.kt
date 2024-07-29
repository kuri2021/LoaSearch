package com.example.loasearch.util.shared

interface SharedPreferenceInterface {
    fun saveApiKey(api:String)
    fun getApiKey():String
    fun deleteApiKey()
}