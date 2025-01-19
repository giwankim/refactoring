package com.giwankim.refactoring.chapter01

data class StatementData(
    val customer: String,
    val performances: List<EnrichedPerformance>,
) {
    val totalAmount: Int
        get() = performances.sumOf { it.amount }
    val totalVolumeCredits: Int
        get() = performances.sumOf { it.volumeCredits }

    companion object {
        fun create(
            invoice: Invoice,
            plays: Map<String, Play>,
        ): StatementData = StatementData(invoice.customer, invoice.performances.map { EnrichedPerformance.create(it, plays) })
    }
}

data class EnrichedPerformance(
    val playID: String,
    val audience: Int,
    val play: Play,
    var amount: Int,
    var volumeCredits: Int,
) {
    companion object {
        fun create(
            performance: Performance,
            plays: Map<String, Play>,
        ): EnrichedPerformance {
            val play =
                plays[performance.playID] ?: throw IllegalArgumentException("unknown playID: ${performance.playID}")
            val calculator = PerformanceCalculator.create(performance, play)
            return EnrichedPerformance(
                performance.playID,
                performance.audience,
                calculator.play,
                calculator.amount,
                calculator.volumeCredits,
            )
        }
    }
}

sealed class PerformanceCalculator(
    val performance: Performance,
    val play: Play,
) {
    abstract val amount: Int

    open val volumeCredits: Int
        get() = maxOf(performance.audience - 30, 0)

    companion object {
        fun create(
            performance: Performance,
            play: Play,
        ): PerformanceCalculator =
            when (play.type) {
                "tragedy" -> TragedyCalculator(performance, play)
                "comedy" -> ComedyCalculator(performance, play)
                else -> throw IllegalArgumentException("unknown type: ${play.type}")
            }
    }
}

class TragedyCalculator(
    performance: Performance,
    play: Play,
) : PerformanceCalculator(performance, play) {
    override val amount: Int
        get() {
            var result = 40_000
            if (performance.audience > 30) {
                result += 1_000 * (performance.audience - 30)
            }
            return result
        }
}

class ComedyCalculator(
    performance: Performance,
    play: Play,
) : PerformanceCalculator(performance, play) {
    override val amount: Int
        get() {
            var result = 30_000
            if (performance.audience > 20) {
                result += 10_000 + 500 * (performance.audience - 20)
            }
            result += 300 * performance.audience
            return result
        }

    override val volumeCredits: Int
        get() = super.volumeCredits + performance.audience / 5
}
