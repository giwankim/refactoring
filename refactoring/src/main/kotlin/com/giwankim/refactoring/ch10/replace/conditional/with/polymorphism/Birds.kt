package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism

fun plumages(birds: List<Bird>): Map<String, Plumage> = birds.associate { it.name to plumage(it) }

fun speeds(birds: List<Bird>): Map<String, Double> = birds.associate { it.name to airSpeedVelocity(it) }

fun plumage(bird: Bird): Plumage = bird.plumage

fun airSpeedVelocity(bird: Bird): Double = bird.airSpeedVelocity
