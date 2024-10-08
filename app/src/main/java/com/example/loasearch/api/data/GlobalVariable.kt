package com.example.loasearch.api.data

import com.example.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.example.loasearch.api.data.challenge_guardian.GetChallengeGuardianData
import com.example.loasearch.api.data.character.GetCharacterData
import com.example.loasearch.api.data.event.GetEventsData
import com.example.loasearch.api.data.get_auctions_option.GetAuctionsOptionsData
import com.example.loasearch.api.data.get_markets_options.Category
import com.example.loasearch.api.data.get_markets_options.GetMarketsOptionsData
import com.example.loasearch.api.data.get_markets_options.Sub
import com.example.loasearch.api.data.market.PostMarketData
import com.example.loasearch.api.data.news.GetNewsData

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