package com.giwankim.refactoring.ch08.move.function

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class AccountTest :
    FunSpec({
        context("bankCharge") {
            context("normal account") {
                test("0 days overdrawn") {
                    val account = Account(0, AccountType.NORMAL)
                    account.backCharge() shouldBe 4.5
                }

                test("x days overdrawn") {
                    listOf(1, 2, 7).forAll {
                        val account = Account(it, AccountType.NORMAL)
                        account.backCharge() shouldBe 4.5 + 1.75 * it
                    }
                }
            }

            context("premium account") {
                test("0 days overdrawn") {
                    val account = Account(0, AccountType.PREMIUM)
                    account.backCharge() shouldBe 4.5
                }

                test("1 ~ 7 days overdrawn") {
                    listOf(1, 2, 7).forAll {
                        val account = Account(it, AccountType.PREMIUM)
                        account.backCharge() shouldBe 4.5 + 10.0
                    }
                }

                test("more than 7 days overdrawn") {
                    listOf(8, 9, 20).forAll {
                        val account = Account(it, AccountType.PREMIUM)
                        account.backCharge() shouldBe 4.5 + 10.0 + (it - 7) * 0.85
                    }
                }
            }
        }
    })
