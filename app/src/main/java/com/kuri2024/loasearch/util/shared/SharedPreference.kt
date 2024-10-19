package com.kuri2024.loasearch.util.shared

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class SharedPreference(var context: Context):SharedPreferenceInterface {

    override fun saveType(type:String){
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("type", type)
        editor.apply()
    }

    override fun saveIdPw(id:String,pw:String){
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("id", id)
        editor.putString("pw",pw)
        editor.apply()
    }

    override fun saveKey(key:String){
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("key", key)
        editor.apply()
    }

    override fun getId():String{
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        return sharedPreference.getString("id", "").toString()
    }

    override fun getPw():String{
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        return sharedPreference.getString("pw", "").toString()
    }

    override fun getType():String{
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        return sharedPreference.getString("type", "").toString()
    }

    override fun getKey():String{
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        return sharedPreference.getString("key", "").toString()
    }

    override fun deleteData(){
        val sharedPreference = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.clear()
        editor.apply()
    }
}