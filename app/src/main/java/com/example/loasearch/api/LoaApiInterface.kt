package com.example.loasearch.api

import com.example.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.example.loasearch.api.data.character.GetCharacterData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LoaApiInterface {
    @GET("/armories/characters/{name}")
    fun getCharacter(
        @Path("name") name:String
    ): Call<GetCharacterData>

    @GET("/gamecontents/challenge-abyss-dungeons")
    fun getChallengeAbyssData(
    ):Call<GetChallengeAbyssData>

    @GET("/news/notices")
    fun getNews(
    ):Call<Any>

    @GET("/news/events")
    fun getEvents(
    ):Call<Any>

    @GET("/gamecontents/challenge-guardian-raids")
    fun getChallengeGuardian(
    ):Call<Any>
}