package com.giwankim.refactoring.ch11.replace.function.with.command

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ScoreTest :
    FunSpec({
        lateinit var mockScoringGuide: ScoringGuide

        beforeTest {
            mockScoringGuide = mockk()
        }

        test("non-smoker from state with regular certification scores 0") {
            val candidate = Candidate(originState = "California")
            val medicalExam = MedicalExam(isSmoker = false)
            every { mockScoringGuide.stateWithLowCertification(any()) } returns false

            val result = score(candidate, medicalExam, mockScoringGuide)

            result shouldBe 0
        }

        test("smoker from state with regular certification loses 5 points") {
            val candidate = Candidate(originState = "New York")
            val medicalExam = MedicalExam(isSmoker = true)
            every { mockScoringGuide.stateWithLowCertification(any()) } returns false

            val result = score(candidate, medicalExam, mockScoringGuide)

            result shouldBe -5 // 0 - (10 - 5)
        }

        test("non-smoker from state with low certification loses 5 points") {
            val candidate = Candidate(originState = "Low Cert State")
            val medicalExam = MedicalExam(isSmoker = false)
            every { mockScoringGuide.stateWithLowCertification(any()) } returns true

            val result = score(candidate, medicalExam, mockScoringGuide)

            result shouldBe -5
        }

        test("smoker from state with low certification loses 10 points") {
            val candidate = Candidate(originState = "Low Cert State")
            val medicalExam = MedicalExam(isSmoker = true)
            every { mockScoringGuide.stateWithLowCertification(any()) } returns true

            val result = score(candidate, medicalExam, mockScoringGuide)

            result shouldBe -10 // -5 - (10 - 5)
        }
    })
