package com.giwankim.refactoring.ch07.remove.middle.man

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PersonTest :
    FunSpec({
        test("get manager") {
            val department = Department().apply { assignManager(Person("manager", this)) }
            val person = Person("worker", department)
            person.department.manager?.name shouldBe "manager"
        }
    })
