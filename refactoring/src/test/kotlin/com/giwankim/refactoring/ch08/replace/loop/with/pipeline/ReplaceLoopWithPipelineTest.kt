package com.giwankim.refactoring.ch08.replace.loop.with.pipeline

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ReplaceLoopWithPipelineTest :
    FunSpec({
        test("acquireData") {
            val input =
                """
                office, country, telephone
                Chicago, USA, +1 312 373 1000
                Beijing, China, +86 4008 900 505
                Bangalore, India, +91 80 4064 9570
                Porto Alegre, Brazil, +55 51 3079 3550
                Chennai, India, +91 44 660 44766
                """.trimIndent()
            val expected =
                listOf(
                    Office("Bangalore", "+91 80 4064 9570"),
                    Office("Chennai", "+91 44 660 44766"),
                )
            acquireData(input) shouldBe expected
        }
    })
