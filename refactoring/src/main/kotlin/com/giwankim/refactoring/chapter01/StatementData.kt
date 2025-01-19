package com.giwankim.refactoring.chapter01

fun createStatementData(
    invoice: Invoice,
    plays: Map<String, Play>,
): StatementData {
    fun playFor(perf: Performance): Play = plays[perf.playID] ?: throw IllegalArgumentException("unknown playID: ${perf.playID}")

    fun amountFor(aPerformance: Performance): Int = PerformanceCalculator(aPerformance, playFor(aPerformance)).amount

    fun volumeCreditsFor(aPerformance: EnrichedPerformance): Int {
        var result = 0
        result += maxOf(aPerformance.audience - 30, 0)
        if ("comedy" == aPerformance.play.type) {
            result += aPerformance.audience / 5
        }
        return result
    }

    fun enrichPerformance(aPerformance: Performance): EnrichedPerformance {
        val calculator = PerformanceCalculator(aPerformance, playFor(aPerformance))
        return EnrichedPerformance(aPerformance.playID, aPerformance.audience, calculator.play).apply {
            amount = amountFor(aPerformance)
            volumeCredits = volumeCreditsFor(this)
        }
    }

    fun totalVolumeCredits(data: StatementData): Int = data.performances.sumOf { it.volumeCredits }

    fun totalAmount(data: StatementData): Int = data.performances.sumOf { it.amount }

    return StatementData(invoice.customer, invoice.performances.map(::enrichPerformance)).apply {
        totalAmount = totalAmount(this)
        totalVolumeCredits = totalVolumeCredits(this)
    }
}

class PerformanceCalculator(
    val performance: Performance,
    val play: Play,
) {
    val amount: Int
        get() {
            var result: Int
            when (play.type) {
                "tragedy" -> {
                    result = 40_000
                    if (performance.audience > 30) {
                        result += 1_000 * (performance.audience - 30)
                    }
                }

                "comedy" -> {
                    result = 30_000
                    if (performance.audience > 20) {
                        result += 10_000 + 500 * (performance.audience - 20)
                    }
                    result += 300 * performance.audience
                }

                else -> throw IllegalArgumentException("unknown type: ${play.type}")
            }
            return result
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
