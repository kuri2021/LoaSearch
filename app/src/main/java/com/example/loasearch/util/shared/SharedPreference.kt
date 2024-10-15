package com.example.loasearch.util.shared

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.util.connet.Connect

class SharedPreference(var context: Context):SharedPreferenceInterface {

    override fun saveIdPw(id:String,pw:String){
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("id", id)
        editor.putString("pw",pw)
        editor.apply()
    }

    override fun saveApiKey(api:String){
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("api", api)
        editor.apply()
        Connect.accessToken = api
    }

    override fun getId():String{
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        return sharedPreference.getString("id", "").toString()
    }

    override fun getPw():String{
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        return sharedPreference.getString("pw", "").toString()
    }

    override fun getApiKey():String{
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        return sharedPreference.getString("api", "").toString()
    }

    override fun deleteData(){
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.clear()
    }
}