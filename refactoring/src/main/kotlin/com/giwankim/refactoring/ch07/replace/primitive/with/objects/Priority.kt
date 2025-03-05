package com.giwankim.refactoring.ch07.replace.primitive.with.objects

@JvmInline
value class Priority(
    val value: String,
) {
    init {
        require(value in legalValues()) { "Invalid priority: $value" }
    }

    private val index: Int get() = legalValues().indexOf(value)

    fun higherThan(other: Priority): Boolean = index > other.index

    fun lowerThan(other: Priority): Boolean = index < other.index

    override fun toString(): String = value

    companion object {
        fun legalValues(): List<String> = listOf("low", "normal", "high", "rush")
    }
}
