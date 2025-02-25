package com.giwankim.refactoring.chapter06.inline.function.refactored

import com.giwankim.refactoring.chapter06.inline.function.Driver

fun rating(driver: Driver) = if (driver.numberOfLateDeliveries > 5) 2 else 1
