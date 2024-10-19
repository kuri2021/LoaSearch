package com.kuri2024.loasearch.api.data.get_auctions_option

data class GetAuctionsOptionsData(
    val Categories: List<Category>,
    val Classes: List<String>,
    val EtcOptions: List<EtcOption>,
    val ItemGradeQualities: List<Int>,
    val ItemGrades: List<String>,
    val ItemTiers: List<Int>,
    val MaxItemLevel: Int,
    val SkillOptions: List<SkillOption>
)