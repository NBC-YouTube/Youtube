package com.nbc.youtube.data.local.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nbc.youtube.presentation.model.UserInfo


@Entity(tableName = "users")
data class UserEntity (
    val name: String,
    @ColumnInfo("profile_thumbnail") val profileThumbnail: Uri,
    @ColumnInfo("channel_thumbnail") val channelThumbnail: Uri,
    val introduction: String,
    @PrimaryKey val id: String,
) {

    fun toPresentation(): UserInfo = UserInfo(
        name = name,
        profileThumbnail = profileThumbnail,
        channelThumbnail = channelThumbnail,
        introduction = introduction,
        id = id,
    )
    companion object {
        const val DEFAULT_USER_ID = "1"

        val userEntity = UserEntity(
            name = "My PoKemon Channel Dummy Dummy",
            profileThumbnail = Uri.parse("android.resource://com.nbc.youtube/drawable/sample_user_thumbnail"),
            channelThumbnail = Uri.parse("android.resource://com.nbc.youtube/drawable/sample_channel_thumbnail"),
            introduction = "Discover the latest Pokémon news, animations, and gameplay on the official Pokémon YouTube channel. Dive into the captivating world of Pokémon and experience everything it has to offer right here!",
            id = DEFAULT_USER_ID,
        )
    }
}