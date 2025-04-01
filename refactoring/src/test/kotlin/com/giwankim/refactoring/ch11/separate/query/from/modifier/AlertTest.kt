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

        test("should find Don and set off alarms") {
            val people = listOf(Person("Normal"), Person("Don"), Person("John"))

            val result = alertForMiscreant(people)

            result?.name shouldBe "Don"
            verify(exactly = 1) { setOffAlarms() }
        }

        test("should find John and set off alarms") {
            val people = listOf(Person("Normal"), Person("John"))

            val result = alertForMiscreant(people)

            result?.name shouldBe "John"
            verify(exactly = 1) { setOffAlarms() }
        }

        test("should return null when no miscreant found") {
            val people = listOf(Person("Normal1"), Person("Normal2"))

            val result = alertForMiscreant(people)

            result shouldBe null
            verify(exactly = 0) { setOffAlarms() }
        }

        test("should return null for empty list") {
            val people: List<Person> = emptyList()

            val result = alertForMiscreant(people)

            result shouldBe null
            verify(exactly = 0) { setOffAlarms() }
        }
    })
