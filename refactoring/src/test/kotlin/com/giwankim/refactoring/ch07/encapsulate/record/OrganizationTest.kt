package com.giwankim.refactoring.ch07.encapsulate.record

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OrganizationTest :
    FunSpec({
        afterTest {
            organization = Organization(name = "Acme Gooseberries", country = "GB")
        }

        test("read attribute") {
            var result = ""
            result += "<h1>${organization().name}</h1>"
            result shouldBe "<h1>Acme Gooseberries</h1>"
        }

        test("write attribute") {
            organization().name = "New Acme Gooseberries"
            organization().name shouldBe "New Acme Gooseberries"
        }
    })
