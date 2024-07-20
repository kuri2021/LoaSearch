package com.example.loasearch.api.data.character

data class Collectible(
    val CollectiblePoints: List<com.example.loasearch.api.data.character.CollectiblePoint>,
    val Icon: String,
    val MaxPoint: Int,
    val Point: Int,
    val Type: String
)