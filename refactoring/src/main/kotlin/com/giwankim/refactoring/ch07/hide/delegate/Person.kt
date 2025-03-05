package com.giwankim.refactoring.ch07.hide.delegate

class Person(
    val name: String,
    var department: Department? = null,
) {
    fun assignDepartment(department: Department) {
        this.department = department
        department.manager = this
    }
}
