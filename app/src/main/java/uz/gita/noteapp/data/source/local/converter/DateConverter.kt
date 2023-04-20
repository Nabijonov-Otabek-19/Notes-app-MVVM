package uz.gita.noteapp.data.source.local.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateConverter {
    @TypeConverter
    fun fromDateToTimeStamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromTimeStampToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    fun getCurrentTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")
        return current.format(formatter)
    }
}