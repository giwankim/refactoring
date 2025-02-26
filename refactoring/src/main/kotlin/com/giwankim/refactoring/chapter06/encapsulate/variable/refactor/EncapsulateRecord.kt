package com.giwankim.refactoring.chapter06.encapsulate.variable.refactor

typealias StringMap = MutableMap<String, String>

var defaultOwnerData: StringMap = mutableMapOf("firstName" to "Martin", "lastName" to "Fowler")

fun defaultOwner() = Person(defaultOwnerData)

fun setDefaultOwner(arg: StringMap) {
    defaultOwnerData = arg
}
