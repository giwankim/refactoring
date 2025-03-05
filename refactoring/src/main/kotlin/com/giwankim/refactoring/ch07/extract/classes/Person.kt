package com.giwankim.refactoring.ch07.extract.classes

class Person(
    var name: String,
    officeAreaCode: String,
    officeNumber: String,
) {
    private val _telephoneNumber = TelephoneNumber(areaCode = officeAreaCode, number = officeNumber)

    val telephoneNumber: String
        get() = _telephoneNumber.toString()

    var officeAreaCode: String
        get() = _telephoneNumber.areaCode
        set(value) {
            _telephoneNumber.areaCode = value
        }

    var officeNumber: String
        get() = _telephoneNumber.number
        set(value) {
            _telephoneNumber.number = value
        }
}
