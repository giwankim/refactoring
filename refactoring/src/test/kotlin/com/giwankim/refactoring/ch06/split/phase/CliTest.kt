package com.giwankim.refactoring.ch06.split.phase

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
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
