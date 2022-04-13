package es.magonxesp.elma.shared.domain.date

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class DateUtils {
    companion object {
        const val format = "yyyy-MM-dd HH:mm:ss"

        fun parse(date: String): LocalDateTime {
            try {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format))
            } catch (exception: DateTimeParseException) {
                throw InvalidDateTimeFormatException("Invalid date format, the date format should be $format")
            }
        }

        fun now(): String {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format))
        }

        fun format(date: LocalDateTime): String {
            return date.format(DateTimeFormatter.ofPattern(format))
        }
    }
}