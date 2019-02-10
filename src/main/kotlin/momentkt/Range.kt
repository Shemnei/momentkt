package momentkt

import java.time.LocalDate

class DateRange(override val start: LocalDate, override val endInclusive: LocalDate) : ClosedRange<LocalDate>, Iterable<LocalDate> {

    override fun iterator(): Iterator<LocalDate> {
        return object : Iterator<LocalDate> {
            var tmp = start
            override fun hasNext(): Boolean {
                return tmp <= endInclusive
            }

            override fun next(): LocalDate {
                tmp = tmp.plusDays(1)
                return tmp
            }
        }
    }
}

operator fun LocalDate.rangeTo(other: LocalDate): DateRange {
    return DateRange(this, other)
}