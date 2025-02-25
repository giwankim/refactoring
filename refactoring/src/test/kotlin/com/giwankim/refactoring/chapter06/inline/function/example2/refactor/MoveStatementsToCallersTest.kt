package com.giwankim.refactoring.chapter06.inline.function.example2.refactor

import com.giwankim.refactoring.chapter06.inline.function.example2.Customer
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MoveStatementsToCallersTest {
    @Test
    fun reportLines() {
        val customer = Customer("John", "Seoul")
        val lines = reportLines(customer)
        lines shouldBe listOf(listOf("name", "John"), listOf("location", "Seoul"))
    }
}
