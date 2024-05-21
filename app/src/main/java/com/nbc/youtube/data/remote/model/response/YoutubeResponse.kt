package com.nbc.youtube.data.remote.model.response

data class YoutubeResponse<T>(
    val etag: String?,
    val items: List<Item<T>>?,
    val kind: String?,
    val nextPageToken: String?,
    val pageInfo: PageInfo?,
)