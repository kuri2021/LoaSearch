package com.example.loasearch.api.data.market.options

data class GetMarketOptionsData(
    val Categories: List<Category>,
    val Classes: List<String>,
    val ItemGrades: List<String>,
    val ItemTiers: List<Int>
)