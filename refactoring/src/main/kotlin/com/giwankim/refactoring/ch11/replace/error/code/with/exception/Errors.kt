package com.giwankim.refactoring.ch11.replace.error.code.with.exception

data class ErrorData(
    val order: Order,
    val errorCode: Int?,
)

class OrderProcessingError(
    val errorCode: Int,
    message: String = "Order processing error $errorCode",
) : RuntimeException(message) {
    val name: String
        get() = "OrderProcessingError"
}
