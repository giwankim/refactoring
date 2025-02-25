package com.giwankim.refactoring.chapter06.extract.function

import java.time.LocalDateTime

class Clock {
    companion object {
        fun today() = LocalDateTime.now()
    }
}
