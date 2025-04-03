package com.giwankim.refactoring.ch11.replace.constructor.with.factory.function

class Employee(
    val name: String,
    val typeCode: Char,
) {
    val type: String? = legalTypeCodes[typeCode]

    companion object {
        val legalTypeCodes: Map<Char, String>
            get() =
                mapOf(
                    'E' to "Engineer",
                    'M' to "Manager",
                    'S' to "Salesman",
                )
    }
}
