package com.example.loasearch.api.data.character

data class ArmoryEngraving(
    val ArkPassiveEffects: Any,
    val Effects: List<com.example.loasearch.api.data.character.EffectX>,
    val Engravings: List<com.example.loasearch.api.data.character.Engraving>
)