package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism

fun plumages(birds: List<Bird>): Map<String, Plumage> = birds.associate { it.name to plumage(it) }

fun speeds(birds: List<Bird>): Map<String, Double> = birds.associate { it.name to airSpeedVelocity(it) }

fun plumage(bird: Bird): Plumage =
    when (bird.type) {
        "EuropeanSwallow" -> Plumage.AVERAGE
        "AfricanSwallow" -> if (bird.numberOfCoconuts > 2) Plumage.TIRED else Plumage.AVERAGE
        "NorwegianBlueParrot" -> if (bird.voltage > 100) Plumage.SCORCHED else Plumage.BEAUTIFUL
        else -> Plumage.UNKNOWN
    }

fun airSpeedVelocity(bird: Bird): Double =
    when (bird.type) {
        "EuropeanSwallow" -> 35.0
        "AfricanSwallow" -> 40.0 - 2 * bird.numberOfCoconuts
        "NorwegianBlueParrot" -> if (bird.isNailed) 0.0 else 10 + bird.voltage / 10.0
        else -> Double.MIN_VALUE
    }
