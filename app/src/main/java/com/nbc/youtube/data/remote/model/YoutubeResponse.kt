package com.nbc.youtube.data.remote.model

data class YoutubeResponse(
    val etag: String?,
    val items: List<Item>?,
    val kind: String?,
    val nextPageToken: String?,
    val pageInfo: PageInfo?,
)