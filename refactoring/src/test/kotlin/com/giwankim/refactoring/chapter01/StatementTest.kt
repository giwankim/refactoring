package com.giwankim.refactoring.chapter01

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class StatementTest :
    FunSpec({
        context("given invoice and plays") {
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

            test("should return plain text statement") {
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

            test("should return html statement") {
                val expected =
                    """
                    <h1>Statement for BigCo</h1>
                    <table>
                    <tr><th>play</th><th>seats</th><th>cost</th></tr>
                      <tr><td>Hamlet</td><td>55</td><td>$650.00</td></tr>
                      <tr><td>As You Like It</td><td>35</td><td>$580.00</td></tr>
                      <tr><td>Othello</td><td>40</td><td>$500.00</td></tr>
                    </table>
                    <p>Amount owed is <em>$1,730.00</em></p>
                    <p>You earned <em>47</em> credits</p>
                    
                    """.trimIndent()

                htmlStatement(invoice, plays) shouldBe expected
            }
        }
    })
