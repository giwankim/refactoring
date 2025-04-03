package com.giwankim.refactoring.ch11.remove.setting.method

class Person {
    var name: String = ""
        set(value) {
            field = value
        }
    var id: String = ""
        set(value) {
            field = value
        }
}
