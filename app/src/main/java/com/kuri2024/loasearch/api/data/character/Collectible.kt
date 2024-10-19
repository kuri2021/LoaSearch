package com.kuri2024.loasearch.api.data.character

data class Collectible(
    val CollectiblePoints: List<CollectiblePoint>,
    val Icon: String,
    val MaxPoint: Int,
    val Point: Int,
    val Type: String
)