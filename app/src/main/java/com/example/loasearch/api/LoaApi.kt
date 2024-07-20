package com.example.loasearch.api

import android.util.Log
import com.example.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.example.loasearch.api.data.character.GetCharacterData
import com.example.loasearch.util.Connect
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoaApi:LoaApiInf {

    companion object {
        lateinit var character: GetCharacterData
        lateinit var challengeAbyss : GetChallengeAbyssData
    }

    val api = Connect().connect().create(LoaApiInterface::class.java)

    override fun getCharacterData(name:String,callback:(String)->Unit){
        api.getCharacter(name).enqueue(object : Callback<GetCharacterData>{
            override fun onResponse(call: Call<GetCharacterData>, response: Response<GetCharacterData>) {
                val body = response.body()
                Log.d("getCharacterData",body.toString())
                Log.d("getCharacterData",response.message())
                Log.d("getCharacterData",response.code().toString())
                Log.d("getCharacterData",response.raw().toString())
                if (body!=null){
                    character = body
                    callback("성공")
                }else{
                    callback("실패")
                }
            }

            override fun onFailure(call: Call<GetCharacterData>, t: Throwable) {
                callback("통신 실패 : ${call}/${t}")
            }

        })
    }

    fun getChallengeAbyssData(callback:(String)->Unit){
        api.getChallengeAbyssData().enqueue(object : Callback<GetChallengeAbyssData>{
            override fun onResponse(call: Call<GetChallengeAbyssData>, response: Response<GetChallengeAbyssData>) {
                val body = response.body()
                if (body!=null){
                    callback("성공")
                    challengeAbyss = body
                }else{
                    callback("실패")
                }
            }

            override fun onFailure(call: Call<GetChallengeAbyssData>, t: Throwable) {
                callback("통신 실패 : ${call}/${t}")
            }

        })
    }

    fun getNews(){

    }
}