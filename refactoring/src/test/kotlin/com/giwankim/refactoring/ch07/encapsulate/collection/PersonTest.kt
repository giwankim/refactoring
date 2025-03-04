package com.giwankim.refactoring.ch07.encapsulate.collection

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PersonTest :
    FunSpec({
        val basicCourseNames = listOf("Spring", "JPA", "Kotlin")

        test("set courses") {
            val person = Person("John Doe", mutableListOf())
            person.courses = basicCourseNames.map { Course(it, false) }.toMutableList()
            person.courses shouldBe listOf(Course("Spring", false), Course("JPA", false), Course("Kotlin", false))
        }

        test("add courses") {
            val person = Person("John Doe", mutableListOf())
            basicCourseNames.forEach { person.addCourse(Course(it, false)) }
            person.courses shouldBe listOf(Course("Spring", false), Course("JPA", false), Course("Kotlin", false))
        }
    })
