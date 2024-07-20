package com.example.loasearch.api.data.character

data class GetCharacterData(
    val ArmoryAvatars: List<com.example.loasearch.api.data.character.ArmoryAvatar>,
    val ArmoryCard: com.example.loasearch.api.data.character.ArmoryCard,
    val ArmoryEngraving: com.example.loasearch.api.data.character.ArmoryEngraving,
    val ArmoryEquipment: List<com.example.loasearch.api.data.character.ArmoryEquipment>,
    val ArmoryGem: com.example.loasearch.api.data.character.ArmoryGem,
    val ArmoryProfile: com.example.loasearch.api.data.character.ArmoryProfile,
    val ArmorySkills: List<com.example.loasearch.api.data.character.ArmorySkill>,
    val Collectibles: List<com.example.loasearch.api.data.character.Collectible>,
    val ColosseumInfo: com.example.loasearch.api.data.character.ColosseumInfo
)