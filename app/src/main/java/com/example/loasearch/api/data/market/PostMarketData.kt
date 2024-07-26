package com.example.loasearch.api.data.market

data class PostMarketData(
    val Items: List<Item>,
    val PageNo: Int,
    val PageSize: Int,
    val TotalCount: Int
)