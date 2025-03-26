package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism.variation

fun rating(
    voyage: Voyage,
    history: History,
): String = Rating(voyage, history).value
