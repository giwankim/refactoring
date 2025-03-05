package com.giwankim.refactoring.ch07.hide.delegate

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PersonTest :
    FunSpec({
        test("get manager") {
            val manager = Person("Uncle Bob")
            val department = Department("123", manager)
            val person = Person("Coder", department)
            person.department?.manager?.name shouldBe "Uncle Bob"
        }
    })
