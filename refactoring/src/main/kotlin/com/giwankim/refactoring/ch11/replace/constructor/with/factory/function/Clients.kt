package com.giwankim.refactoring.ch11.replace.constructor.with.factory.function

fun client(document: Document) {
    val candidate = Employee(document.name, document.empType)
}

fun client2(document: Document) {
    val leadEngineer = Employee(document.leadEngineer, 'E')
}
