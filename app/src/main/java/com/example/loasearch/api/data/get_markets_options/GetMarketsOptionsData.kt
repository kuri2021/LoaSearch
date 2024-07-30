package com.example.loasearch.api.data.get_markets_options

data class GetMarketsOptionsData(
    val Categories: List<Category>,
    val Classes: List<String>,
    val ItemGrades: List<String>,
    val ItemTiers: List<Int>
)