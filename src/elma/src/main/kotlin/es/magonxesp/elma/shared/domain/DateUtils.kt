package es.magonxesp.elma.shared.domain

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateUtils {
    companion object {
        const val format = "yyyy-MM-dd HH:mm:ss"

        fun parse(date: String): LocalDateTime {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format))
        }

        fun now(): String {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format))
        }
    }
}