package com.example.loasearch.api.data.get_auctions_option

data class Category(
    val Code: Int,
    val CodeName: String,
    val Subs: List<Sub>
)