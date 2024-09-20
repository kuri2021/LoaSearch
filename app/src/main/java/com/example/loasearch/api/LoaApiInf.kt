package com.example.loasearch.api

import com.example.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.example.loasearch.api.data.challenge_guardian.GetChallengeGuardianData
import com.example.loasearch.api.data.event.GetEventsData
import com.example.loasearch.api.data.get_auctions_option.GetAuctionsOptionsData
import com.example.loasearch.api.data.get_markets_options.GetMarketsOptionsData
import com.example.loasearch.api.data.market.PostMarketData
import com.example.loasearch.api.data.news.GetNewsData
import com.example.loasearch.api.data.post_auctions.PostAuctionsItemData
import com.example.loasearch.api.data.post_auctions.etc_options.EtcOptions
import com.example.loasearch.api.data.post_auctions.skill_options.SkillOptions

interface LoaApiInf {
    fun getCharacterData(name:String,callback:(String)->Unit)
    fun getNews(callback: (GetNewsData?,String) -> Unit)
    fun getEvents(callback: (GetEventsData?, String) -> Unit)
    fun getChallengeGuardian(callback: (GetChallengeGuardianData?, String) -> Unit)
    fun getChallengeAbyss(callback: (GetChallengeAbyssData?, String) -> Unit)
    fun getMarketOptions(callback: (GetMarketsOptionsData?, String) -> Unit)
    fun postMarkets(
        sort: String,
        code: Int,
        characterClass: String,
        itemTier: String,
        itemGrade: String,
        itemName: String,
        pageNo: Int,
        sortCondition: String,
        callback: (PostMarketData) -> Unit
    )
    fun getAuctionsOptions(callback: (GetAuctionsOptionsData?, String) -> Unit)
    fun postAuctions(
        sort: String,
        categoryCode: Int,
        characterClass: String,
        itemTier: String,
        itemGrade: String,
        itemName: String,
        pageNo: Int,
        sortCondition: String,
        levelMin:Int,
        levelMax:Int,
        gradeQuality:String?,
        callback: (PostAuctionsItemData) -> Unit
    )
}