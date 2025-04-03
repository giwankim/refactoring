package com.giwankim.refactoring.ch11.replace.function.with.command

fun score(
    candidate: Candidate,
    medicalExam: MedicalExam,
    scoringGuide: ScoringGuide,
): Int = Scorer(candidate, medicalExam, scoringGuide).execute()
