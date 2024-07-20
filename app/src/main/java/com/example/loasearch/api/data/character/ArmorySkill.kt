package com.example.loasearch.api.data.character

data class ArmorySkill(
    val Icon: String,
    val IsAwakening: Boolean,
    val Level: Int,
    val Name: String,
    val Rune: com.example.loasearch.api.data.character.Rune,
    val Tooltip: String,
    val Tripods: List<com.example.loasearch.api.data.character.Tripod>,
    val Type: String
)