package com.giwankim.refactoring.ch06.extract.function

import java.time.LocalDateTime

class Clock {
    companion object {
        fun today() = LocalDateTime.now()
    }
}
