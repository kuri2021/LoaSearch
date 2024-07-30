package com.example.loasearch.api.data.post_auctions_item

data class PostAuctionsItemData(
    val Items: List<Item>,
    val PageNo: Int,
    val PageSize: Int,
    val TotalCount: Int
)