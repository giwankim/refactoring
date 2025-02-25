package com.giwankim.refactoring.chapter06.inline.function

fun rating(driver: Driver) = if (moreThanFiveLateDeliveries(driver)) 2 else 1

fun moreThanFiveLateDeliveries(driver: Driver) = driver.numberOfLateDeliveries > 5
