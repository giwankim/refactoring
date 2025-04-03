package com.giwankim.refactoring.ch11.replace.query.with.parameter

import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify

class ControlTest :
    FunSpec({

        beforeTest {
            mockkObject(Thermostat)
            mockkStatic(::setToHeat, ::setToCool, ::setOff)

            // Mock the heating/cooling methods
            justRun { setToHeat() }
            justRun { setToCool() }
            justRun { setOff() }
        }

        afterTest {
            unmockkAll()
        }

        test("should set to heat when target temperature is higher than current") {
            val heatingPlan = HeatingPlan(max = 30, min = 15)
            every { Thermostat.selectedTemperature } returns 25
            every { Thermostat.currentTemperature } returns 20

            control(heatingPlan)

            verify(exactly = 1) { setToHeat() }
            verify(exactly = 0) { setToCool() }
            verify(exactly = 0) { setOff() }
        }

        test("should set to cool when target temperature is lower than current") {
            val heatingPlan = HeatingPlan(max = 30, min = 15)
            every { Thermostat.selectedTemperature } returns 20
            every { Thermostat.currentTemperature } returns 25

            control(heatingPlan)

            verify(exactly = 0) { setToHeat() }
            verify(exactly = 1) { setToCool() }
            verify(exactly = 0) { setOff() }
        }

        test("should set to off when target temperature equals current") {
            val heatingPlan = HeatingPlan(max = 30, min = 15)
            every { Thermostat.selectedTemperature } returns 22
            every { Thermostat.currentTemperature } returns 22

            control(heatingPlan)

            verify(exactly = 0) { setToHeat() }
            verify(exactly = 0) { setToCool() }
            verify(exactly = 1) { setOff() }
        }

        test("should use min value when selected temperature is below min") {
            val heatingPlan = HeatingPlan(max = 30, min = 20)
            every { Thermostat.selectedTemperature } returns 15
            every { Thermostat.currentTemperature } returns 18

            control(heatingPlan)

            verify(exactly = 1) { setToHeat() }
            verify(exactly = 0) { setToCool() }
            verify(exactly = 0) { setOff() }
        }

        test("should use max value when selected temperature is above max") {
            val heatingPlan = HeatingPlan(max = 25, min = 15)
            every { Thermostat.selectedTemperature } returns 30
            every { Thermostat.currentTemperature } returns 28

            control(heatingPlan)

            verify(exactly = 0) { setToHeat() }
            verify(exactly = 1) { setToCool() }
            verify(exactly = 0) { setOff() }
        }
    })
