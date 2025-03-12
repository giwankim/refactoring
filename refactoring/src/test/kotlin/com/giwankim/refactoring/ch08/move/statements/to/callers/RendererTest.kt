package com.giwankim.refactoring.ch08.move.statements.to.callers

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.time.LocalDateTime

class RendererTest :
    FunSpec({
        test("listRecentPhotos") {
            mockkStatic("com.giwankim.refactoring.ch08.move.statements.to.callers.RendererKt")
            every { recentDateCutoff() } returns LocalDateTime.of(2025, 3, 11, 0, 0)

            val outputStream = ByteArrayOutputStream()
            val photos =
                listOf(
                    Photo(title = "Rocket", location = "SpaceX", date = LocalDateTime.of(2025, 3, 10, 0, 0), data = "🚀"),
                    Photo(title = "Dog", location = "Park", date = LocalDateTime.of(2025, 3, 12, 0, 0), data = "🐶"),
                    Photo(title = "Tada!", location = "Party", date = LocalDateTime.of(2025, 3, 13, 0, 0), data = "🎉"),
                )

            listRecentPhotos(PrintStream(outputStream), photos)

            outputStream.toString() shouldBe
                """
                <div>
                <p>title: Dog</p>
                <p>date: 2025. 3. 12. 오전 12:00:00</p>
                <p>location: Park</p>
                </div>
                <div>
                <p>title: Tada!</p>
                <p>date: 2025. 3. 13. 오전 12:00:00</p>
                <p>location: Party</p>
                </div>
                
                """.trimIndent()

            unmockkStatic("com.giwankim.refactoring.ch08.move.statements.to.callers.RendererKt")
        }

        test("renderPerson") {
            val outputStream = ByteArrayOutputStream()
            val photo = Photo(title = "Snoopy", location = "Peanuts", date = LocalDateTime.of(2025, 3, 10, 0, 0), data = "🐶")
            val person = Person("Charlie Brown", photo)

            renderPerson(
                PrintStream(outputStream),
                person,
            )

            outputStream.toString() shouldBe
                """
                <p>Charlie Brown</p>
                🐶
                <p>title: Snoopy</p>
                <p>date: 2025. 3. 10. 오전 12:00:00</p>
                <p>location: Peanuts</p>
                
                """.trimIndent()
        }
    })
