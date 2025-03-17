package com.giwankim.refactoring.ch09.split.variable

fun discount(
    originalInputValue: Int,
    quantity: Int,
): Int {
    var inputValue = originalInputValue
    if (inputValue > 50) {
        inputValue -= 2
    }
    if (quantity > 100) {
        inputValue -= 1
    }
    return inputValue
}
