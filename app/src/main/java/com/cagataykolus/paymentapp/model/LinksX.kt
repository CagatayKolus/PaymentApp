package com.cagataykolus.paymentapp.model

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class LinksX(
    val lang: String,
    val logo: String,
    val operation: String,
    val self: String,
    val validation: String
)


class LinksXConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun toLinksX(data: String?): LinksX {
            if (data == null) {
                return LinksX("empty","empty","empty","empty","empty")
            }
            val listType = object : TypeToken<LinksX>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromLinksX(movies: LinksX): String {
            return gson.toJson(movies)
        }
    }
}