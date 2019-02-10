package momentkt

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

fun LocalDate.format(pattern: String, locale: Locale = Locale.getDefault()): String {
    return this.format(DateTimeFormatter.ofPattern(pattern, locale))
}

fun LocalDate.iso(): String {
    return this.format(DateTimeFormatter.ISO_DATE)
}

fun LocalTime.format(pattern: String, locale: Locale = Locale.getDefault()): String {
    return this.format(DateTimeFormatter.ofPattern(pattern, locale))
}

val LocalTime.iso: String
    get () = this.format(DateTimeFormatter.ISO_TIME)

fun LocalDateTime.format(pattern: String, locale: Locale = Locale.getDefault()): String {
    return this.format(DateTimeFormatter.ofPattern(pattern, locale))
}

val LocalDateTime.iso: String
    get () = this.format(DateTimeFormatter.ISO_DATE_TIME)

fun OffsetDateTime.format(pattern: String, locale: Locale = Locale.getDefault()): String {
    return this.format(DateTimeFormatter.ofPattern(pattern, locale))
}

val OffsetDateTime.iso: String
    get () = this.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)

fun OffsetTime.format(pattern: String, locale: Locale = Locale.getDefault()): String {
    return this.format(DateTimeFormatter.ofPattern(pattern, locale))
}

val OffsetTime.iso: String
    get () = this.format(DateTimeFormatter.ISO_OFFSET_TIME)

fun ZonedDateTime.format(pattern: String, locale: Locale = Locale.getDefault()): String {
    return this.format(DateTimeFormatter.ofPattern(pattern, locale))
}

val ZonedDateTime.iso: String
    get () = this.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)

fun Duration.format(pattern: String): String {
    TODO()
}

fun Period.format(pattern: String): String {
    TODO()
}

fun Instant.format(pattern: String): String {
    TODO()
}