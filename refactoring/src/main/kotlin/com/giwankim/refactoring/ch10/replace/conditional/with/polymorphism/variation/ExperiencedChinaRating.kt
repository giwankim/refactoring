package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism.variation

class ExperiencedChinaRating(
    voyage: Voyage,
    history: History,
) : Rating(voyage, history)
