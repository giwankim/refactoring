package com.giwankim.refactoring.chapter06.inline.function.example1.refactor

import com.giwankim.refactoring.chapter06.inline.function.example1.Driver
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InlineFunctionTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 5])
    fun `driver is punctual, then rating is 1`(lateDeliveries: Int) {
        val driver = Driver(lateDeliveries)
        rating(driver) shouldBe 1
    }

    @Test
    fun `driver is late more then 5 times, then rating is 2`() {
        val driver = Driver(6)
        rating(driver) shouldBe 2
    }
}
