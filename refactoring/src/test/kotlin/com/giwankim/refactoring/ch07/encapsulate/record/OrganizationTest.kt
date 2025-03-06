package com.giwankim.refactoring.ch07.encapsulate.record

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OrganizationTest :
    FunSpec({
        lateinit var sut: MutableMap<String, String>

        beforeTest {
            sut = organization.toMutableMap()
        }

        test("read attribute") {
            var result = ""
            result += "<h1>${sut["name"]}</h1>"
            result shouldBe "<h1>Acme Gooseberries</h1>"
        }

        test("write attribute") {
            sut["name"] = "New Acme Gooseberries"
            sut["name"] shouldBe "New Acme Gooseberries"
        }
    })
