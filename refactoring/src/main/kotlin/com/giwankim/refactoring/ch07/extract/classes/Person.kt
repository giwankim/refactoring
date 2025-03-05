package com.giwankim.refactoring.ch07.extract.classes

class Person(
    var name: String,
    officeAreaCode: String,
    officeNumber: String,
) {
    private val _telephoneNumber = TelephoneNumber(officeAreaCode = officeAreaCode, officeNumber = officeNumber)

    val telephoneNumber: String
        get() = _telephoneNumber.telephoneNumber

    var officeAreaCode: String
        get() = _telephoneNumber.officeAreaCode
        set(value) {
            _telephoneNumber.officeAreaCode = value
        }

    var officeNumber: String
        get() = _telephoneNumber.officeNumber
        set(value) {
            _telephoneNumber.officeNumber = value
        }
}
