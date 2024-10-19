package com.kuri2024.loasearch.api.data.post_auctions

data class AuctionInfo(
    val BidCount: Int,
    val BidPrice: Int,
    val BidStartPrice: Int,
    val BuyPrice: Any,
    val EndDate: String,
    val IsCompetitive: Boolean,
    val StartPrice: Int,
    val TradeAllowCount: Int
)