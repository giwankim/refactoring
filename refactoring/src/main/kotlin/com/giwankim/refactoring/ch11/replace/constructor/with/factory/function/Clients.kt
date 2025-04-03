package com.giwankim.refactoring.ch11.replace.constructor.with.factory.function

fun client(document: Document) {
    val candidate = createEmployee(document.name, document.empType)
}

fun client2(document: Document) {
    val leadEngineer = createEmployee(document.leadEngineer, 'E')
}

fun createEmployee(
    name: String,
    typeCode: Char,
): Employee = Employee(name, typeCode)
