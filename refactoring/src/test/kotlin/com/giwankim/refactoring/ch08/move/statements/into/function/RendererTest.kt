package com.giwankim.refactoring.ch08.move.statements.into.function

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime

class RendererTest :
    FunSpec({
        test("renderPerson") {
            val photo = Photo("Snoopy", "fun park", LocalDateTime.of(2025, 3, 14, 15, 9))
            val person = Person("Charlie Brown", photo)

            val result = renderPerson(ByteArrayOutputStream(), person)

            result shouldBe
                """
                <p>Charlie Brown</p>
                🐶
                <p>title: Snoopy</p>
                <p>location: fun park</p>
                <p>date: 2025. 3. 14. 오후 3:09:00</p>

                """.trimIndent()
        }
    })
