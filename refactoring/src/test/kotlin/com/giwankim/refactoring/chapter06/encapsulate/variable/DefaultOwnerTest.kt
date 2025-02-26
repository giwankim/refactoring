package com.giwankim.refactoring.chapter06.encapsulate.variable

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DefaultOwnerTest :
    FunSpec({
        test("encapsulate the value") {
            val owner1 = defaultOwner()
            owner1["lastName"] shouldBe "Fowler"
            val owner2 = defaultOwner()
            owner2["lastName"] = "Parsons"
            owner1["lastName"] shouldBe "Parsons" // is this ok?
        }
    })
