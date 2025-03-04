package com.giwankim.refactoring.ch07.encapsulate.collection

class Person(
    val name: String,
    courseList: List<Course>,
) {
    private var _courses = courseList.toMutableList()

    var courses: List<Course>
        get() = _courses
        set(value) {
            _courses = value.toMutableList()
        }

    fun addCourse(course: Course) {
        _courses.add(course)
    }

    fun removeCourse(
        course: Course,
        fnIfAbsent: () -> Unit = { throw IllegalArgumentException() },
    ) {
        if (!_courses.remove(course)) {
            fnIfAbsent()
        }
    }
}
