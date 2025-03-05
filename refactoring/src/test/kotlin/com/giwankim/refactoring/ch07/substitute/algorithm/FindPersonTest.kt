package com.giwankim.refactoring.ch07.substitute.algorithm

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class FindPersonTest :
    FunSpec({
        context("foundPerson") {
            test("person found") {
                val people = listOf("Don", "John", "Kent")
                foundPerson(people) shouldBe "Don"
            }

            test("person not found") {
                val people = listOf("Martin", "Fowler")
                foundPerson(people) shouldBe ""
            }
        }
    })
