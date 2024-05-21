package com.nbc.youtube.data.local

import android.net.Uri
import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun parseDateToString(date: Date): String {
        return DateUtil.formatDate(date)
    }

    @TypeConverter
    fun parseStringToDate(value: String): Date {
        return DateUtil.parseDate(value)
    }

    @TypeConverter
    fun parseUriTotString(uri: Uri): String {
        return uri.toString()
    }

    @TypeConverter
    fun parseStringToUri(value: String): Uri {
        return Uri.parse(value)
    }
}