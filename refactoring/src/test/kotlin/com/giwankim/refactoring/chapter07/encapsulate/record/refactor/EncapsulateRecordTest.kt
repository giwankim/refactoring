package com.giwankim.refactoring.chapter07.encapsulate.record.refactor

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class EncapsulateRecordTest :
    FunSpec({
        test("getter") {
            var result = ""
            result += "<h1>${organization().name}</h1>"
            result shouldBe "<h1>Acme Gooseberries</h1>"
        }

        test("setter") {
            organization().name = "New Acme Gooseberries"
            organization().name shouldBe "New Acme Gooseberries"
        }
    })
