package com.giwankim.refactoring.ch11.parametrize.function

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SalaryTest :
    FunSpec({

        test("ten percent raise should increase salary by 10%") {
            val person = Person(salary = 1000.0)
            val expectedSalary = 1100.0

            tenPercentRaise(person)

            person.salary shouldBe expectedSalary
        }

        test("five percent raise should increase salary by 5%") {
            val person = Person(salary = 1000.0)
            val expectedSalary = 1050.0

            fivePercentRaise(person)

            person.salary shouldBe expectedSalary
        }

        test("applying both raises should compound correctly") {
            val person = Person(salary = 1000.0)
            val expectedSalary = 1000.0 * 1.1 * 1.05

            tenPercentRaise(person)
            fivePercentRaise(person)

            person.salary shouldBe expectedSalary
        }
    })
