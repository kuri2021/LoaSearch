package com.example.loasearch.api

interface LoaApiInf {
    fun getCharacterData(name:String,callback:(String)->Unit)
}