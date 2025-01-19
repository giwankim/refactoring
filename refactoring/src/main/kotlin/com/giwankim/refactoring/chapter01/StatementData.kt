package com.giwankim.refactoring.chapter01

fun createStatementData(
    invoice: Invoice,
    plays: Map<String, Play>,
): StatementData {
    fun playFor(perf: Performance): Play = plays[perf.playID] ?: throw IllegalArgumentException("unknown playID: ${perf.playID}")

    fun enrichPerformance(aPerformance: Performance): EnrichedPerformance {
        val calculator = createPerformanceCalculator(aPerformance, playFor(aPerformance))
        return EnrichedPerformance(aPerformance.playID, aPerformance.audience, calculator.play).apply {
            amount = calculator.amount
            volumeCredits = calculator.volumeCredits
        }
    }

    fun totalVolumeCredits(data: StatementData): Int = data.performances.sumOf { it.volumeCredits }

    fun totalAmount(data: StatementData): Int = data.performances.sumOf { it.amount }

    return StatementData(invoice.customer, invoice.performances.map(::enrichPerformance)).apply {
        totalAmount = totalAmount(this)
        totalVolumeCredits = totalVolumeCredits(this)
    }
}

data class StatementData(
    val customer: String,
    val performances: List<EnrichedPerformance>,
    var totalAmount: Int = 0,
    var totalVolumeCredits: Int = 0,
)

data class EnrichedPerformance(
    val playID: String,
    val audience: Int,
    val play: Play,
    var amount: Int = 0,
    var volumeCredits: Int = 0,
)

fun createPerformanceCalculator(
    performance: Performance,
    play: Play,
): PerformanceCalculator =
    when (play.type) {
        "tragedy" -> TragedyCalculator(performance, play)
        "comedy" -> ComedyCalculator(performance, play)
        else -> throw IllegalArgumentException("unknown type: ${play.type}")
    }

sealed class PerformanceCalculator(
    val performance: Performance,
    val play: Play,
) {
    open val amount: Int
        get() {
            throw NotImplementedError("subclass responsibility")
        }

    open val volumeCredits: Int
        get() = maxOf(performance.audience - 30, 0)
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
