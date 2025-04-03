package com.giwankim.refactoring.ch11.remove.setting.method

class Person(
    val id: String,
) {
    var name: String = ""
        set(value) {
            field = value
        }
}
