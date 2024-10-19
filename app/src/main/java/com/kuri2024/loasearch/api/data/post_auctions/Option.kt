package com.kuri2024.loasearch.api.data.post_auctions

data class Option(
    val ClassName: String,
    val IsPenalty: Boolean,
    val IsValuePercentage: Boolean,
    val OptionName: String,
    val OptionNameTripod: String,
    val Type: String,
    val Value: Int
)