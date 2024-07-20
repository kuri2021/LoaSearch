package com.example.loasearch.api.data.challenge_abyss

data class ChallengeAbyssDataItem(
    val AreaName: String,
    val Description: String,
    val EndTime: String,
    val Image: String,
    val MinCharacterLevel: Int,
    val MinItemLevel: Int,
    val Name: String,
    val RewardItems: List<RewardItem>,
    val StartTime: String
)