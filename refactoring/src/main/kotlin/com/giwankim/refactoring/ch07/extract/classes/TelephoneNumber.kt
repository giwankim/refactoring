package com.giwankim.refactoring.ch07.extract.classes

class TelephoneNumber(
    var officeAreaCode: String,
    var officeNumber: String,
) {
    val telephoneNumber: String
        get() = "($officeAreaCode) $officeNumber"
}
