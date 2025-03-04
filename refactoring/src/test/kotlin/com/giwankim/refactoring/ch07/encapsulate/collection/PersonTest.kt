package com.giwankim.refactoring.ch07.encapsulate.collection

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class PersonTest :
    FunSpec({
        val basicCourseNames = listOf("Spring", "JPA", "Kotlin")

        context("set courses") {
            test("setter updates courses as a single value") {
                val person = Person("John Doe", listOf())
                person.courses = basicCourseNames.map { Course(it, false) }.toMutableList()
                person.courses shouldBe listOf(Course("Spring", false), Course("JPA", false), Course("Kotlin", false))
            }

            test("setter makes a copy of the course collection") {
                val person = Person("John Doe", listOf())
                val courses = mutableListOf(Course("Spring", false), Course("JPA", false), Course("Kotlin", false))

                person.courses = courses
                courses.removeLast()

                person.courses shouldBe listOf(Course("Spring", false), Course("JPA", false), Course("Kotlin", false))
            }
        }

        test("add courses") {
            val person = Person("John Doe", listOf())
            basicCourseNames.forEach { person.addCourse(Course(it, false)) }
            person.courses shouldBe listOf(Course("Spring", false), Course("JPA", false), Course("Kotlin", false))
        }

        context("remove course") {
            test("enrolled in the course") {
                val person = Person("John Doe", listOf(Course("Spring", false), Course("JPA", false)))
                person.removeCourse(Course("Spring", false))
                person.courses shouldNotContain Course("Spring", false)
            }

            test("if not enrolled in the course, throw an exception") {
                val person = Person("John Doe", listOf(Course("Spring", false), Course("JPA", false)))
                shouldThrow<IllegalArgumentException> { person.removeCourse(Course("Kotlin", false)) }
            }
        }
    })
