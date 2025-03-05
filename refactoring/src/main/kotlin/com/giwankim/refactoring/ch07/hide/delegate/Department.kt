package com.giwankim.refactoring.ch07.hide.delegate

class Department(
    var chargeCode: String,
    var manager: Person,
) {
    init {
        manager.department = this
    }
}
