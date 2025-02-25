package com.giwankim.refactoring.chapter06.inline.function.example1.refactor

import com.giwankim.refactoring.chapter06.inline.function.example1.Driver

fun rating(driver: Driver) = if (driver.numberOfLateDeliveries > 5) 2 else 1
