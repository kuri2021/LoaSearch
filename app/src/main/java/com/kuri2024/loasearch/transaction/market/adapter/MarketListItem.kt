package com.kuri2024.loasearch.transaction.market.adapter

class MarketListItem(
    var icon:String,
    var currentMinPrice:Int,
    var name:String,
    var recentPrice:Int,
    var tradeRemainCount:Any?,
    var yDayAvgPrice:Int,
    var grade:String
) {
}