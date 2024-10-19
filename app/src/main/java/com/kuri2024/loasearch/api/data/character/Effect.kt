package com.kuri2024.loasearch.api.data.character

data class Effect(
    val CardSlots: List<Int>,
    val Index: Int,
    val Items: List<Item>
)