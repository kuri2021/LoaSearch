package com.example.loasearch.api.data.market

data class Item(
    val BundleCount: Int,
    val CurrentMinPrice: Int,
    val Grade: String,
    val Icon: String,
    val Id: Int,
    val Name: String,
    val RecentPrice: Int,
    val TradeRemainCount: Any,
    val YDayAvgPrice: Int
)