package com.example.loasearch.util.shared

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.util.connet.Connect

class SharedPreference(var context: Context):SharedPreferenceInterface {

    override fun saveApiKey(api:String){
        val sharedPreference = context.getSharedPreferences("api", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("api", api)
        editor.apply()
        Connect.accessToken = api
    }

    override fun getApiKey():String{
        val sharedPreference = context.getSharedPreferences("api", AppCompatActivity.MODE_PRIVATE)
        return sharedPreference.getString("api", "").toString()
    }

    override fun deleteApiKey(){
        val sharedPreference = context.getSharedPreferences("api", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.remove("api")

    }
}