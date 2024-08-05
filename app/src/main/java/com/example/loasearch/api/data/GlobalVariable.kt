package com.example.loasearch.api.data

import com.example.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.example.loasearch.api.data.challenge_guardian.GetChallengeGuardianData
import com.example.loasearch.api.data.character.GetCharacterData
import com.example.loasearch.api.data.event.GetEventsData
import com.example.loasearch.api.data.get_markets_options.GetMarketsOptionsData
import com.example.loasearch.api.data.news.GetNewsData

object GlobalVariable {
    var character: GetCharacterData? = null
    var challengeAbyss: GetChallengeAbyssData? = null
    var challengeGuardian: GetChallengeGuardianData? = null
    var news: GetNewsData? = null
    var events: GetEventsData? = null

//    마켓 옵션
    var marketOption : GetMarketsOptionsData? = null
}