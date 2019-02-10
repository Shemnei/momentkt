package momentkt

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period

val Duration.ago: LocalDateTime
    get() = LocalDateTime.now() - this

val Period.ago: LocalDateTime
    get() = LocalDateTime.now() - this

val Duration.fromNow: LocalDate
    get() = LocalDate.now() + this

val Period.fromNow: LocalDate
    get() = LocalDate.now() + this


operator fun Duration.unaryPlus(): Duration = if (this.isNegative) this.negated() else this
operator fun Duration.unaryMinus(): Duration = if (this.isNegative) this else this.negated()

operator fun Period.unaryPlus(): Period = if (this.isNegative) this.negated() else this
operator fun Period.unaryMinus(): Period = if (this.isNegative) this else this.negated()

operator fun LocalDate.inc(): LocalDate = this.plusDays(1)
operator fun LocalDate.dec(): LocalDate = this.minusDays(1)