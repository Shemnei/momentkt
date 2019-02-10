package momentkt

import java.time.*

fun LocalDateTime.reZone(zoneFrom: ZoneId, zoneTo: ZoneId): LocalDateTime {
    return this.atZone(zoneFrom).withZoneSameInstant(zoneTo).toLocalDateTime()
}

fun LocalDateTime.atUTC(zoneFrom: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return this.atZone(zoneFrom).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()
}

fun LocalDateTime.atLocal(zoneTo: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return this.atZone(ZoneId.of("UTC")).withZoneSameInstant(zoneTo).toLocalDateTime()
}

fun LocalTime.reZone(offsetFrom: ZoneOffset = OffsetTime.now().offset, offsetTo: ZoneOffset): LocalTime {
    return this.atOffset(offsetFrom).withOffsetSameInstant(offsetTo).toLocalTime()
}

fun LocalTime.atUTC(offsetFrom: ZoneOffset = OffsetTime.now().offset): LocalTime {
    return this.atOffset(offsetFrom).withOffsetSameInstant(ZoneOffset.ofHours(0)).toLocalTime()
}

fun LocalTime.atLocal(offsetTo: ZoneOffset = OffsetTime.now().offset): LocalTime {
    return this.atOffset(ZoneOffset.ofHours(0)).withOffsetSameInstant(offsetTo).toLocalTime()
}