package com.kuri2024.loasearch.api

import com.kuri2024.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.kuri2024.loasearch.api.data.challenge_guardian.GetChallengeGuardianData
import com.kuri2024.loasearch.api.data.character.GetCharacterData
import com.kuri2024.loasearch.api.data.event.GetEventsData
import com.kuri2024.loasearch.api.data.get_auctions_option.GetAuctionsOptionsData
import com.kuri2024.loasearch.api.data.get_markets_options.GetMarketsOptionsData
import com.kuri2024.loasearch.api.data.market.PostMarketData
import com.kuri2024.loasearch.api.data.news.GetNewsData
import com.kuri2024.loasearch.api.data.post_auctions.PostAuctionsItemData
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

    @GET("/auctions/options")
    fun getAuctionsOptions(
    ):Call<GetAuctionsOptionsData>

    @POST("/auctions/items")
    fun postAuctions(
        @Body parameters: HashMap<String, Any?>
    ):Call<PostAuctionsItemData>

    @GET("/markets/options")
    fun getMarketsOptions(
    ):Call<GetMarketsOptionsData>

    @POST("markets/items")
    fun postMarkets(
        @Body parameters: HashMap<String, Any>
    ):Call<PostMarketData>
}