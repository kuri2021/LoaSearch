package com.kuri2024.loasearch.api.data.market

data class PostMarketData(
    val Items: List<Item>,
    val PageNo: Int,
    val PageSize: Int,
    val TotalCount: Int
)