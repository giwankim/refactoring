package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism.variation

class History(
    val voyages: List<Voyage>,
) : List<Voyage> by voyages
