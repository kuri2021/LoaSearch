package com.kuri2024.loasearch.api.data.market.options

data class Category(
    val Code: Int,
    val CodeName: String,
    val Subs: List<Sub>
)