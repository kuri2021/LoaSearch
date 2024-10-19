package com.kuri2024.loasearch.api.data.get_auctions_option

data class SkillOption(
    val Class: String,
    val IsSkillGroup: Boolean,
    val Text: String,
    val Tripods: List<Tripod>,
    val Value: Int
)