package com.giwankim.refactoring.ch11.replace.function.with.command

import kotlin.math.max

fun score(
    candidate: Candidate,
    medicalExam: MedicalExam,
    scoringGuide: ScoringGuide,
): Int = Scorer().execute(candidate, medicalExam, scoringGuide)

class Scorer {
    fun execute(
        candidate: Candidate,
        medicalExam: MedicalExam,
        scoringGuide: ScoringGuide,
    ): Int {
        var result = 0
        var healthLevel = 0
        var highMedicalRiskFlag = false

        if (medicalExam.isSmoker) {
            healthLevel += 10
            highMedicalRiskFlag = true
        }
        var certificationGrade = "regular"
        if (scoringGuide.stateWithLowCertification(candidate.originState)) {
            certificationGrade = "low"
            result -= 5
        }
        // lots more code like this
        result -= max(healthLevel - 5, 0)
        return result
    }
}
