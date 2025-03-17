package com.giwankim.refactoring.ch09.split.variable

fun discount(
    inputValue: Int,
    quantity: Int,
): Int {
    var result = inputValue
    if (result > 50) {
        result -= 2
    }
    if (quantity > 100) {
        result -= 1
    }
    return result
}
