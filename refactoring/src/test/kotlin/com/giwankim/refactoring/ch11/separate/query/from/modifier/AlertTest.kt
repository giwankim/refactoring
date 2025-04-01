package com.giwankim.refactoring.ch11.separate.query.from.modifier

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockkStatic
import io.mockk.verify

class AlertTest :
    FunSpec({

        beforeTest {
            mockkStatic("com.giwankim.refactoring.ch11.separate.query.from.modifier.AlertKt")
        }

        context("Don and John are included") {
            val people = listOf(Person("Normal"), Person("Don"), Person("John"))

            test("should find Don") {
                val miscreant = findMiscreant(people)
                miscreant?.name shouldBe "Don"
            }

            test("should set off alarms for John") {
                alertForMiscreant(people)
                verify(exactly = 1) { setOffAlarms() }
            }
        }

        context("only John is included") {
            val people = listOf(Person("Normal"), Person("John"))

            test("should find John and set off alarms") {
                val miscreant = findMiscreant(people)
                miscreant?.name shouldBe "John"
            }

            test("should set off alarms for John") {
                alertForMiscreant(people)
                verify(exactly = 1) { setOffAlarms() }
            }
        }

        context("no miscreants") {
            val people = listOf(Person("Normal1"), Person("Normal2"))

            test("should return null when no miscreant found") {
                findMiscreant(people) shouldBe null
            }

            test("should not set off alarms") {
                alertForMiscreant(people)
                verify(exactly = 0) { setOffAlarms() }
            }
        }

        context("empty people list") {
            val people: List<Person> = emptyList()

            test("should return null for empty list") {
                findMiscreant(people) shouldBe null
            }

            test("should not set off alarms") {
                alertForMiscreant(people)
                verify(exactly = 0) { setOffAlarms() }
            }
        }
    })
