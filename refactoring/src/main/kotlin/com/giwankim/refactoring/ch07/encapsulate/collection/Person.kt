package com.giwankim.refactoring.ch07.encapsulate.collection

class Person(
    val name: String,
    var courses: MutableList<Course>,
) {
    fun addCourse(course: Course) {
        courses.add(course)
    }

    fun removeCourse(
        course: Course,
        fnIfAbsent: () -> Unit = { throw IllegalArgumentException() },
    ) {
        if (!courses.remove(course)) {
            fnIfAbsent()
        }
    }
}
