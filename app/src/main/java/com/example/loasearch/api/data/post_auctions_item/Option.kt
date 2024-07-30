package com.example.loasearch.api.data.post_auctions_item

data class Option(
    val ClassName: String,
    val IsPenalty: Boolean,
    val IsValuePercentage: Boolean,
    val OptionName: String,
    val OptionNameTripod: String,
    val Type: String,
    val Value: Int
)