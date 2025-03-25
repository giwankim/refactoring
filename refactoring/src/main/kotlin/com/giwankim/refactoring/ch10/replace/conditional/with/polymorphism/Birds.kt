package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism

fun plumages(birds: List<Bird>): Map<String, Plumage> =
    birds
        .map(::createBird)
        .associate { it.name to it.plumage }

fun speeds(birds: List<Bird>): Map<String, Double> =
    birds
        .map(::createBird)
        .associate { it.name to it.airSpeedVelocity }

fun createBird(bird: Bird): Bird =
    when (bird.type) {
        "EuropeanSwallow" ->
            EuropeanSwallow(
                name = bird.name,
                numberOfCoconuts = bird.numberOfCoconuts,
                voltage = bird.voltage,
                isNailed = bird.isNailed,
            )

        "AfricanSwallow" ->
            AfricanSwallow(
                name = bird.name,
                numberOfCoconuts = bird.numberOfCoconuts,
                voltage = bird.voltage,
                isNailed = bird.isNailed,
            )

        "NorwegianBlueParrot" ->
            NorwegianBlueParrot(
                name = bird.name,
                numberOfCoconuts = bird.numberOfCoconuts,
                voltage = bird.voltage,
                isNailed = bird.isNailed,
            )

        else ->
            Bird(
                name = bird.name,
                type = bird.type,
                numberOfCoconuts = bird.numberOfCoconuts,
                voltage = bird.voltage,
                isNailed = bird.isNailed,
            )
    }
