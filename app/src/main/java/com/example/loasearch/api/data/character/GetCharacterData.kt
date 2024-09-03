package com.example.loasearch.api.data.character

data class GetCharacterData(
    val ArmoryAvatars: List<ArmoryAvatar>,
    val ArmoryCard: ArmoryCard,
    val ArmoryEngraving: ArmoryEngraving,
    val ArmoryEquipment: List<ArmoryEquipment>,
    val ArmoryGem: ArmoryGem,
    val ArmoryProfile: ArmoryProfile,
    val ArmorySkills: List<ArmorySkill>,
    val Collectibles: List<Collectible>,
    val ColosseumInfo: ColosseumInfo
)