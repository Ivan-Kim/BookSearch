package com.example.booksearch.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@ProvidedTypeConverter
class SearchTimeConverter {
    @TypeConverter
    fun fromString(value: String): LocalDateTime =
        LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    @TypeConverter
    fun fromLocalDate(value: LocalDateTime): String = value.toString()
}
