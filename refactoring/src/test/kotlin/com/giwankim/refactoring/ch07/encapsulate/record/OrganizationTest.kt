package com.giwankim.refactoring.ch07.encapsulate.record

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OrganizationTest :
    FunSpec({
        afterTest {
            organization = mutableMapOf("name" to "Acme Gooseberries", "country" to "GB")
        }

        test("read attribute") {
            var result = ""
            result += "<h1>${getRawDataOfOrganization()["name"]}</h1>"
            result shouldBe "<h1>Acme Gooseberries</h1>"
        }

        test("write attribute") {
            getRawDataOfOrganization()["name"] = "New Acme Gooseberries"
            getRawDataOfOrganization()["name"] shouldBe "New Acme Gooseberries"
        }
    })
