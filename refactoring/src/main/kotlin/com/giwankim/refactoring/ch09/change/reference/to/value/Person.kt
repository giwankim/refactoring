package com.giwankim.refactoring.ch09.change.reference.to.value

class Person {
    private var telephoneNumber: TelephoneNumber = TelephoneNumber("", "")
    var officeAreaCode: String
        get() = telephoneNumber.areaCode
        set(value) {
            telephoneNumber = TelephoneNumber(value, officeNumber)
        }
    var officeNumber: String
        get() = telephoneNumber.number
        set(value) {
            telephoneNumber = TelephoneNumber(officeAreaCode, value)
        }
}
