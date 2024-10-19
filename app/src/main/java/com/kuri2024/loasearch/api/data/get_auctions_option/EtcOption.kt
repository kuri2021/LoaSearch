package com.kuri2024.loasearch.api.data.get_auctions_option

data class EtcOption(
    val EtcSubs: List<EtcSub>,
    val Text: String,
    val Tiers: List<Int>,
    val Value: Int
)