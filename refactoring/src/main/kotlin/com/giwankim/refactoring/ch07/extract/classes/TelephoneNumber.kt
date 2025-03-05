package com.giwankim.refactoring.ch07.extract.classes

data class TelephoneNumber(
    var areaCode: String,
    var number: String,
) {
    override fun toString(): String = "($areaCode) $number"
}
