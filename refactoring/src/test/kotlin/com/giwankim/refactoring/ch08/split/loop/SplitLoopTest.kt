package com.giwankim.refactoring.ch08.split.loop

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SplitLoopTest :
    FunSpec({
        test("process") {
            val people =
                listOf(
                    Person(age = 20, salary = 50_000),
                    Person(age = 30, salary = 100_000),
                    Person(age = 40, salary = 150_000),
                )
            process(people) shouldBe "youngestAge: 20, total salary: 300000"
        }
    })
