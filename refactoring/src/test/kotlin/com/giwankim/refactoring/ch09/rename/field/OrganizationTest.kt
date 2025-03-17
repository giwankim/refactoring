package com.giwankim.refactoring.ch09.rename.field

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OrganizationTest :
    FunSpec({
        test("create organization from data") {
            val organization = Organization(mapOf("title" to "Acme Gooseberries", "country" to "GB"))
            organization shouldBe Organization(title = "Acme Gooseberries", country = "GB")
        }
    })
