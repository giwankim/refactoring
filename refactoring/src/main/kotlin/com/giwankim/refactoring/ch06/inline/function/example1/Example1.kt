package com.giwankim.refactoring.ch06.inline.function.example1

fun rating(driver: Driver) = if (moreThanFiveLateDeliveries(driver)) 2 else 1

fun moreThanFiveLateDeliveries(driver: Driver) = driver.numberOfLateDeliveries > 5
