package com.giwankim.refactoring.chapter01

import kotlinx.serialization.Serializable

@Serializable
data class Performance(
    val playID: String,
    val audience: Int,
)
