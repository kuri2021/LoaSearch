package com.example.loasearch.api.data.post_auctions

data class Item(
    val AuctionInfo: AuctionInfo,
    val Grade: String,
    val GradeQuality: Any,
    val Icon: String,
    val Level: Any,
    val Name: String,
    val Options: List<Option>,
    val Tier: Int
)