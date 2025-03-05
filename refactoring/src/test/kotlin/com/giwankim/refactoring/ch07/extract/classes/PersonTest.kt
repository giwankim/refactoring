package com.giwankim.refactoring.ch07.extract.classes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PersonTest :
    FunSpec({
        test("area code") {
            val person = Person(name = "John", officeAreaCode = "010", officeNumber = "1234-5678")
            person.officeAreaCode = "011"
            person.officeAreaCode shouldBe "011"
        }

        test("office number") {
            val person = Person(name = "John", officeAreaCode = "010", officeNumber = "1234-5678")
            person.officeNumber = "5678-1234"
            person.officeNumber shouldBe "5678-1234"
        }

        test("telephone number") {
            val person = Person(name = "John", officeAreaCode = "010", officeNumber = "1234-5678")
            person.telephoneNumber shouldBe "(010) 1234-5678"
        }
    })
