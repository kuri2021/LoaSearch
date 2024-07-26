package com.example.loasearch.api

import com.example.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.example.loasearch.api.data.challenge_guardian.GetChallengeGuardianData
import com.example.loasearch.api.data.character.GetCharacterData
import com.example.loasearch.api.data.event.GetEventsData
import com.example.loasearch.api.data.market.PostMarketData
import com.example.loasearch.api.data.news.GetNewsData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoaApiInterface {
    @GET("/armories/characters/{name}")
    fun getCharacter(
        @Path("name") name:String
    ): Call<GetCharacterData>

    @GET("/news/notices")
    fun getNews(
    ):Call<GetNewsData>

    @GET("/news/events")
    fun getEvents(
    ):Call<GetEventsData>

    @GET("/gamecontents/challenge-abyss-dungeons")
    fun getChallengeAbyssData(
    ):Call<GetChallengeAbyssData>

    @GET("/gamecontents/challenge-guardian-raids")
    fun getChallengeGuardian(
    ):Call<GetChallengeGuardianData>

    @POST("markets/items")
    fun postMarkets(
        @Body parameters: HashMap<String, Any>
    ):Call<PostMarketData>
}