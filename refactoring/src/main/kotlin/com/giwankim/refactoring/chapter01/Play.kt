package com.giwankim.refactoring.chapter01

import kotlinx.serialization.Serializable

@Serializable
data class Play(
    val name: String,
    val type: String,
)
