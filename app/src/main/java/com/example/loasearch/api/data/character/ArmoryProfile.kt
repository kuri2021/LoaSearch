package com.example.loasearch.api.data.character

data class ArmoryProfile(
    val ArkPassive: com.example.loasearch.api.data.character.ArkPassive,
    val CharacterClassName: String,
    val CharacterImage: String,
    val CharacterLevel: Int,
    val CharacterName: String,
    val ExpeditionLevel: Int,
    val GuildMemberGrade: String,
    val GuildName: String,
    val ItemAvgLevel: String,
    val ItemMaxLevel: String,
    val PvpGradeName: String,
    val ServerName: String,
    val Stats: List<com.example.loasearch.api.data.character.Stat>,
    val Tendencies: List<com.example.loasearch.api.data.character.Tendency>,
    val Title: String,
    val TotalSkillPoint: Int,
    val TownLevel: Int,
    val TownName: String,
    val UsingSkillPoint: Int
)