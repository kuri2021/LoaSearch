package com.example.loasearch.api.data.character

data class Effect(
    val CardSlots: List<Int>,
    val Index: Int,
    val Items: List<com.example.loasearch.api.data.character.Item>
)