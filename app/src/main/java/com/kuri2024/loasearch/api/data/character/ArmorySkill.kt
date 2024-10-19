package com.kuri2024.loasearch.api.data.character

data class ArmorySkill(
    val Icon: String,
    val IsAwakening: Boolean,
    val Level: Int,
    val Name: String,
    val Rune: Rune,
    val Tooltip: String,
    val Tripods: List<Tripod>,
    val Type: String
)