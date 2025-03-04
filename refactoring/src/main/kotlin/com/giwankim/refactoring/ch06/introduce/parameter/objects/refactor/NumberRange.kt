package com.giwankim.refactoring.ch06.introduce.parameter.objects.refactor

data class NumberRange(
    val min: Int,
    val max: Int,
) {
    fun contains(number: Int): Boolean = number in min..max
}
