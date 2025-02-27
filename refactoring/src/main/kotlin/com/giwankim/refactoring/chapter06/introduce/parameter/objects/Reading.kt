package com.giwankim.refactoring.chapter06.introduce.parameter.objects

import java.time.LocalDateTime

data class Reading(
    val temp: Int,
    val time: LocalDateTime,
)
