package com.example.loasearch.api

import com.example.loasearch.api.data.market.PostMarketData

interface LoaApiInf {
    fun getCharacterData(name:String,callback:(String)->Unit)
    fun getNews(callback: (String) -> Unit)
    fun getEvents(callback: (String) -> Unit)
    fun getChallengeGuardian(callback: (String) -> Unit)
    fun getChallengeAbyss(callback: (String) -> Unit)
    fun getMarketOptions(callback: (String) -> Unit)
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
    fun getAuctionsOptions(callback: (String) -> Unit)
    fun postAuctions(
        sort: String,
        categoryCode: Int,
        characterClass: String,
        itemTier: String,
        itemGrade: String,
        itemName: String,
        pageNo: Int,
        sortCondition: String,
        callback: (PostMarketData) -> Unit
    )
}