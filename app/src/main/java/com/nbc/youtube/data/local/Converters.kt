package com.nbc.youtube.data.local

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
}