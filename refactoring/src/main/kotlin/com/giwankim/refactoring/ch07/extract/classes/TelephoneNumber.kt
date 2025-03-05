package com.giwankim.refactoring.ch07.extract.classes

class TelephoneNumber(
    var areaCode: String,
    var number: String,
) {
    val telephoneNumber: String
        get() = "($areaCode) $number"
}
