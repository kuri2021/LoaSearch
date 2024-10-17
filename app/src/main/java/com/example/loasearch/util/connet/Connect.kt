package com.example.loasearch.util.connet

import android.app.Activity
import android.util.Log
import android.view.WindowManager
import com.example.loasearch.util.shared.SharedPreference
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Connect : ConnectInf {
    companion object{
        var accessToken: String=""
    }
    private lateinit var retrofit: Retrofit
//    private val accessToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAwNTExMjYifQ.hPLEYINABECIth-rTjJX_Xq8en-Bsq6aLZtnXbcIJkhE1EAkCsyShB7iRhsgVzgd1FrD6g5ZOnbLShxbmuyRDiyanmM1lzzjvKNn5N7rF_VPNHo3hVl1LG37HORBKmAf3vaX6IHb6JKMRmbhdfb3Jz34zkF6K5K6pizA2SnjYmHycmvqtVHXYwyhsx24nbfWn3J8JFrvKODlN1wcRxNQRsev1XZz_BRXl1x1D6hgbPvO9cCKPU092npfc3lWtR8_r6wFQ-P0A5Pb7ASKSO1cqg1gMeEjocCnhHj2ccxtBuxGGk0JiYrM7C5Jd0xis3aBiQ_BLSYP5foOZKlYQli7kg"

    override fun connect(): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
        httpClient.addInterceptor {
            val request: Request = it.request().newBuilder().addHeader("authorization", "Bearer $accessToken").build()
            it.proceed(request)
        }

        val gson: Gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl("https://developer-lostark.game.onstove.com").client(httpClient.build()).build()
        return retrofit
    }

    override fun signUpConnect(api:String): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
        httpClient.addInterceptor {
            val request: Request = it.request().newBuilder().addHeader("authorization", "Bearer $api").build()
            it.proceed(request)
        }

        val gson: Gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl("https://developer-lostark.game.onstove.com").client(httpClient.build()).build()
        return retrofit
    }

    override fun connectStart(activity:Activity){
        activity.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun connectEnd(activity: Activity){
        activity.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

}