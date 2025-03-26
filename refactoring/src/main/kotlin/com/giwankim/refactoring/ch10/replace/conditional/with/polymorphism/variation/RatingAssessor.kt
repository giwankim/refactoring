package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism.variation

fun rating(
    voyage: Voyage,
    history: History,
): String = createRating(voyage, history).value

fun createRating(
    voyage: Voyage,
    history: History,
): Rating =
    if (voyage.zone == "china" && history.any { it.zone == "china" }) {
        ExperiencedChinaRating(voyage, history)
    } else {
        Rating(voyage, history)
    }
