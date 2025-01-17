package com.giwankim.refactoring.chapter01

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class StatementTest :
    FunSpec({
        test("statement") {
            val invoice =
                Invoice(
                    "BigCo",
                    listOf(
                        Performance("hamlet", 55),
                        Performance("as-like", 35),
                        Performance("othello", 40),
                    ),
                )
            val plays =
                mapOf(
                    "hamlet" to Play("Hamlet", "tragedy"),
                    "as-like" to Play("As You Like It", "comedy"),
                    "othello" to Play("Othello", "tragedy"),
                )
            val expected =
                """
                Statement for BigCo
                  Hamlet: $650.00 (55 seats)
                  As You Like It: $580.00 (35 seats)
                  Othello: $500.00 (40 seats)
                Amount owed is $1,730.00
                You earned 47 credits
                
                """.trimIndent()

            statement(invoice, plays) shouldBe expected
        }
    })
