package com.giwankim.refactoring.ch10.replace.nested.conditional.with.guard.clauses

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PayCalculatorTest :
    FunSpec({
        context("payAmount") {
            test("isSeparated") {
                val employee = Employee(isSeparated = true, isRetired = false)
                payAmount(employee) shouldBe PayResult(0.0, "SEP")
            }

            test("isRetired") {
                val employee = Employee(isSeparated = false, isRetired = true)
                val result = payAmount(employee)
                result shouldBe PayResult(0.0, "RET")
            }

            test("neither isSeparated nor isRetired") {
                val employee = Employee(isSeparated = false, isRetired = false)
                payAmount(employee) shouldBe PayResult(10.0, "OK")
            }
        }
    })
