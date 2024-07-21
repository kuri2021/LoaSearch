package com.example.loasearch.api

import android.util.Log
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.example.loasearch.api.data.challenge_guardian.GetChallengeGuardianData
import com.example.loasearch.api.data.character.GetCharacterData
import com.example.loasearch.api.data.event.GetEventsData
import com.example.loasearch.api.data.news.GetNewsData
import com.example.loasearch.util.Connect
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoaApi : LoaApiInf {
    val api: LoaApiInterface = Connect().connect().create(LoaApiInterface::class.java)

    override fun getCharacterData(name: String, callback: (String) -> Unit) {
        api.getCharacter(name).enqueue(object : Callback<GetCharacterData> {
            override fun onResponse(
                call: Call<GetCharacterData>,
                response: Response<GetCharacterData>
            ) {
                val body = response.body()
                Log.d("getCharacterData", body.toString())
                Log.d("getCharacterData", response.message())
                Log.d("getCharacterData", response.code().toString())
                Log.d("getCharacterData", response.raw().toString())
                if (body != null) {
                    GlobalVariable.character = body
                    callback("성공")
                } else {
                    callback("실패")
                }
            }

            override fun onFailure(call: Call<GetCharacterData>, t: Throwable) {
                callback("통신 실패 : ${call}/${t}")
            }

        })
    }

    fun getNews(callback: (String) -> Unit) {
        api.getNews().enqueue(object : Callback<GetNewsData> {
            override fun onResponse(call: Call<GetNewsData>, response: Response<GetNewsData>) {
                val body = response.body()
                val code = response.code()
                Log.d("getNews", body.toString())
                Log.d("getNews", response.message())
                Log.d("getNews", response.code().toString())
                Log.d("getNews", response.raw().toString())
                if (body != null && code == 200) {
                    GlobalVariable.news = body
                    callback("완료")
                }
            }

            override fun onFailure(call: Call<GetNewsData>, t: Throwable) {
                Log.d("getNews", "${call}/${t}")
                callback("실패")
            }

        })
    }

    //    fun getEvents(callback: (String) -> Unit) {
    fun getEvents(callback: (String) -> Unit) {
        api.getEvents().enqueue(object : Callback<GetEventsData> {
            override fun onResponse(call: Call<GetEventsData>, response: Response<GetEventsData>) {
                val body = response.body()
                val code = response.code()
                Log.d("getEvents", body.toString())
                Log.d("getEvents", response.message())
                Log.d("getEvents", response.code().toString())
                Log.d("getEvents", response.raw().toString())
                if (body != null && code == 200) {
                    GlobalVariable.events = body
                    callback("완료")
                }
            }

            override fun onFailure(call: Call<GetEventsData>, t: Throwable) {
                Log.d("getEvents", "${call}/${t}")
            }

        })
    }

    //    fun getChallengeGuardian(callback: (String) -> Unit) {
    fun getChallengeGuardian(callback: (String) -> Unit) {
        api.getChallengeGuardian().enqueue(object : Callback<GetChallengeGuardianData> {
            override fun onResponse(
                call: Call<GetChallengeGuardianData>,
                response: Response<GetChallengeGuardianData>
            ) {
                val body = response.body()
                val code = response.code()
                Log.d("getChallengeGuardian", body.toString())
                Log.d("getChallengeGuardian", response.message())
                Log.d("getChallengeGuardian", response.code().toString())
                Log.d("getChallengeGuardian", response.raw().toString())
                if (body != null && code == 200)  {
                    GlobalVariable.challengeGuardian = body
                    callback("완료")
                }
            }

            override fun onFailure(call: Call<GetChallengeGuardianData>, t: Throwable) {
                Log.d("getChallengeGuardian", "${call}/${t}")
            }

        })
    }

    //    fun getChallengeAbyssData(callback: (String) -> Unit) {
    fun getChallengeAbyss(callback: (String) -> Unit) {
        api.getChallengeAbyssData().enqueue(object : Callback<GetChallengeAbyssData> {
            override fun onResponse(call: Call<GetChallengeAbyssData>, response: Response<GetChallengeAbyssData>) {
                val body = response.body()
                val code = response.code()
                if (body != null && code == 200)  {
                    GlobalVariable.challengeAbyss = body
                    callback("완료")
                }
            }

            override fun onFailure(call: Call<GetChallengeAbyssData>, t: Throwable) {
                Log.d("getChallengeAbyss", "${call}/${t}")
            }

        })
    }

}