package com.kuri2024.loasearch.api.data.get_markets_options

data class Category(
    val Code: Int,
    val CodeName: String,
    val Subs: List<Sub>
)