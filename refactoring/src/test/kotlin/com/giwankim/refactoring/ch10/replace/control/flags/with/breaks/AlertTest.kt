package com.giwankim.refactoring.ch10.replace.control.flags.with.breaks

import io.kotest.core.spec.style.FunSpec
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify

class AlertTest :
    FunSpec({
        afterTest { unmockkAll() }

        test("should not send alert when list is empty") {
            mockkStatic(::sendAlert)
            checkSecurity(emptyList())
            verify(exactly = 0) { sendAlert() }
        }

        test("should not send alert when neither Don nor John is in the list") {
            mockkStatic(::sendAlert)
            checkSecurity(
                listOf(
                    Person("Alice"),
                    Person("Bob"),
                ),
            )
            verify(exactly = 0) { sendAlert() }
        }

        test("should send alert once when Don is in the list") {
            mockkStatic(::sendAlert)
            checkSecurity(listOf(Person("Alice"), Person("Don"), Person("Bob")))
            verify(exactly = 1) { sendAlert() }
        }

        test("should send alert once when John is in the list") {
            mockkStatic(::sendAlert)
            checkSecurity(listOf(Person("Alice"), Person("John"), Person("Bob")))
            verify(exactly = 1) { sendAlert() }
        }

        test("should send alert only once when both Don and John are in the list") {
            mockkStatic(::sendAlert)
            checkSecurity(listOf(Person("Alice"), Person("Don"), Person("John")))
            verify(exactly = 1) { sendAlert() }
        }

        test("should send alert for the first match encountered") {
            mockkStatic(::sendAlert)
            checkSecurity(listOf(Person("John"), Person("Don")))
            verify(exactly = 1) { sendAlert() }
        }
    })
