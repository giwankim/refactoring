package com.giwankim.learn

import com.giwankim.refactoring.chapter01.Invoice
import com.giwankim.refactoring.chapter01.Performance
import com.giwankim.refactoring.chapter01.Play
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json

class DeserializationTest :
    FunSpec({
        context("Deserialization") {
            test("plays") {
                val playsJson =
                    """
                    {
                      "hamlet": {"name": "Hamlet", "type": "tragedy"},
                      "as-like": {"name": "As You Like It", "type": "comedy"},
                      "othello": {"name": "Othello", "type": "tragedy"}
                    }
                    """.trimIndent()

                val plays = Json.decodeFromString<Map<String, Play>>(playsJson)

                plays shouldBe
                    mapOf<String, Play>(
                        "hamlet" to Play(name = "Hamlet", type = "tragedy"),
                        "as-like" to Play(name = "As You Like It", type = "comedy"),
                        "othello" to Play(name = "Othello", type = "tragedy"),
                    )
            }

            test("invoices") {
                val invoicesJson =
                    """
                    [
                      {
                        "customer": "BigCo",
                        "performances": [
                          {
                            "playID": "hamlet",
                            "audience": 55
                          },
                          {
                            "playID": "as-like",
                            "audience": 35
                          },
                          {
                            "playID": "othello",
                            "audience": 40
                          }
                        ]
                      }
                    ]
                    """.trimIndent()

                val invoices = Json.decodeFromString<List<Invoice>>(invoicesJson)

                invoices shouldBe
                    listOf(
                        Invoice(
                            "BigCo",
                            listOf(
                                Performance("hamlet", 55),
                                Performance("as-like", 35),
                                Performance("othello", 40),
                            ),
                        ),
                    )
            }
        }
    })
