package com.giwankim.refactoring.ch07.replace.primitive.with.objects

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class PriorityTest :
    FunSpec({
        test("higher than") {
            assertSoftly {
                Priority("low").higherThan(Priority("normal")) shouldBe false
                Priority("normal").higherThan(Priority("low")) shouldBe true
                Priority("high").higherThan(Priority("rush")) shouldBe false
                Priority("rush").higherThan(Priority("high")) shouldBe true
            }
        }

        test("lower than") {
            assertSoftly {
                Priority("low").lowerThan(Priority("normal")) shouldBe true
                Priority("normal").lowerThan(Priority("low")) shouldBe false
                Priority("high").lowerThan(Priority("rush")) shouldBe true
                Priority("rush").lowerThan(Priority("high")) shouldBe false
            }
        }

        test("should throw exception, if value is outside of legal values") {
            shouldThrow<IllegalArgumentException> { Priority("invalid") }
        }
    })
