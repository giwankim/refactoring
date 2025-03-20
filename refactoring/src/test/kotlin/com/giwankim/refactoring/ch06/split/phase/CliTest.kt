package com.giwankim.refactoring.ch06.split.phase

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import java.lang.IllegalArgumentException
import java.nio.charset.StandardCharsets
import java.nio.file.Files

class CliTest :
    FunSpec({
        lateinit var tempFile: File

        beforeTest {
            tempFile = Files.createTempFile("orders", ".json").toFile()
        }

        afterTest {
            tempFile.delete()
        }

        context("run") {
            test("exit with error when no filename is supplied") {
                val exception = shouldThrow<IllegalArgumentException> { run(emptyArray()) }
                exception.message shouldBe "must supply a filename"
            }

            test("count number of orders") {
                tempFile.writeText("""[{"status": "ready"}, {"status": "pending"}, {"status": "completed"}]""")
                run(arrayOf(tempFile.absolutePath)) shouldBe 3
            }

            test("count number of orders whose status is READY") {
                tempFile.writeText("""[{"status": "ready"}, {"status": "pending"}, {"status": "ready"}]""")
                run(arrayOf("-r", tempFile.absolutePath)) shouldBe 2
            }
        }

        context("main") {
            test("count number of orders") {
                tempFile.writeText("""[{"status": "ready"}, {"status": "pending"}, {"status": "completed"}]""")
                val output =
                    capturingStdOut {
                        main(arrayOf(tempFile.absolutePath))
                    }
                output.trim().toInt() shouldBe 3
            }

            test("count only the orders whose status is ready") {
                tempFile.writeText("""[{"status": "ready"}, {"status": "pending"}, {"status": "ready"}]""")
                val output =
                    capturingStdOut {
                        main(arrayOf("-r", tempFile.absolutePath))
                    }
                output.trim().toInt() shouldBe 2
            }
        }
    })

private fun capturingStdOut(function: () -> Unit): String {
    val outputStream = ByteArrayOutputStream()
    val oldStdOut = System.out
    System.setOut(PrintStream(outputStream))
    try {
        function.invoke()
        return outputStream.toString(StandardCharsets.UTF_8)
    } finally {
        System.setOut(oldStdOut)
    }
}
