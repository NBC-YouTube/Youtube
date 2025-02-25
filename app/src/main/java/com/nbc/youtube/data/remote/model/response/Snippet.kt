package com.nbc.youtube.data.remote.model.response

data class Snippet(
    val categoryId: String?,
    val channelId: String?,
    val channelTitle: String?,
    val defaultAudioLanguage: String?,
    val defaultLanguage: String?,
    val description: String?,
    val liveBroadcastContent: String?,
    val localized: Localized?,
    val publishedAt: String?,
    val tags: List<String>?,
    val thumbnails: Thumbnails?,
    val assignable: Boolean?,
    val title: String?,
)