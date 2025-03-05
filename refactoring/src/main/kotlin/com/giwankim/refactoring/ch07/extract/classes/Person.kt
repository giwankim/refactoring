package com.giwankim.refactoring.ch07.extract.classes

class Person(
    var name: String,
    officeAreaCode: String,
    officeNumber: String,
) {
    val telephoneNumber = TelephoneNumber(officeAreaCode = officeAreaCode, officeNumber = officeNumber)

    var officeAreaCode: String
        get() = telephoneNumber.officeAreaCode
        set(value) {
            telephoneNumber.officeAreaCode = value
        }
}
