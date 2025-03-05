package com.giwankim.refactoring.ch07.replace.primitive.with.objects

@JvmInline
value class Priority(
    val value: String,
) {
    override fun toString(): String = value
}
