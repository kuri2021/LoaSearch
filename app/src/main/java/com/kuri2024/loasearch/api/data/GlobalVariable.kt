package com.kuri2024.loasearch.api.data

import com.kuri2024.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.kuri2024.loasearch.api.data.challenge_guardian.GetChallengeGuardianData
import com.kuri2024.loasearch.api.data.event.GetEventsData
import com.kuri2024.loasearch.api.data.get_auctions_option.GetAuctionsOptionsData
import com.kuri2024.loasearch.api.data.get_markets_options.GetMarketsOptionsData
import com.kuri2024.loasearch.api.data.news.GetNewsData


object GlobalVariable {
    var news: GetNewsData? = null
    var events: GetEventsData? = null
    var challengeAbyss: GetChallengeAbyssData? = null
    var challengeGuardian: GetChallengeGuardianData? = null
    //    거래소 옵션
    var marketOption: GetMarketsOptionsData? = null
    //    경매장 옵션
    var auctionOption: GetAuctionsOptionsData? = null

    fun resetData(){
        news = null
        events = null
        challengeAbyss = null
        challengeGuardian = null
        marketOption = null
        auctionOption = null
    }
}