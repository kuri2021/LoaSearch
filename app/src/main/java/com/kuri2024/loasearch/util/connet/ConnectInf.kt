package com.kuri2024.loasearch.util.connet

import android.app.Activity
import retrofit2.Retrofit

interface ConnectInf {
    fun connect(): Retrofit
    fun signUpConnect(api:String): Retrofit
    fun connectStart(activity: Activity)
    fun connectEnd(activity: Activity)
}