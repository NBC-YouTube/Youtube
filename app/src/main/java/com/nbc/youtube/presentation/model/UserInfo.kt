package com.nbc.youtube.presentation.model

import android.net.Uri
import java.util.UUID

data class UserInfo (
    val name: String,
    val profileThumbnail: Uri,
    val channelThumbnail: Uri,
    val introduction: String,
    val id: String = UUID.randomUUID().toString(),
)
