package com.example.loasearch.util.shared

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.util.connet.Connect

class SharedPreference(var context: Context):SharedPreferenceInterface {

    override fun saveApiKey(api:String){
        val sharedPreference = context.getSharedPreferences("api", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("api", api)
        editor.apply()
        Log.d("tagwe",sharedPreference.getString("api", "").toString())
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