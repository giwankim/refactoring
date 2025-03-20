package com.giwankim.refactoring.ch06.split.phase

import io.kotest.core.spec.style.FunSpec
import java.io.File
import java.nio.file.Files

class CliTest :
    FunSpec({
        lateinit var tempFile: File

        beforeEach {
            tempFile = Files.createTempFile("orders", ".json").toFile()
        }

        afterEach {
            tempFile.delete()
        }

        test("return total number of orders when -r flag is not provided") {
            tempFile.writeText("""[{"status": "ready"}, {"status": "pending"}]""")
        }
    })
