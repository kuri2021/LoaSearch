package com.example.loasearch.api.data.get_auctions_option

data class Tripod(
    val IsGem: Boolean,
    val Text: String,
    val Tiers: List<Int>,
    val Value: Int
)