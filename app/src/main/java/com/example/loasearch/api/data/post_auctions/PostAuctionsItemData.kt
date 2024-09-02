package com.example.loasearch.api.data.post_auctions

data class PostAuctionsItemData(
    val Items: List<Item>,
    val PageNo: Int,
    val PageSize: Int,
    val TotalCount: Int
)