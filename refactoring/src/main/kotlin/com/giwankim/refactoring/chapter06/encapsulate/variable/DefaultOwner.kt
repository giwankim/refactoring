package com.giwankim.refactoring.chapter06.encapsulate.variable

typealias StringMap = MutableMap<String, String>

var defaultOwnerData: StringMap = mutableMapOf("firstName" to "Martin", "lastName" to "Fowler")

fun defaultOwner() = defaultOwnerData

fun setDefaultOwner(arg: StringMap) {
    defaultOwnerData = arg
}
