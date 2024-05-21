package com.nbc.youtube.data.remote.model.response

data class Item<T>(
    val etag: String?,
    val id: T?,
    val kind: String?,
    val snippet: Snippet?,
)