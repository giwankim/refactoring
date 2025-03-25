package com.giwankim.refactoring.ch10.replace.nested.conditional.with.guard.clauses

fun payAmount(employee: Employee): PayResult {
    var result: PayResult
    if (employee.isSeparated) {
        result = PayResult(0.0, "SEP")
    } else {
        if (employee.isRetired) {
            result = PayResult(0.0, "RET")
        } else {
            // logic to compute amount
            // lorem.ipsum(dolor.sitAmet)
            // consectetur(adipiscing).elit()
            // sed.do.eiusmod = tempor.incididunt.ut(labore) && dolore(magna.aliqua)
            // ut.enim.ad(minim.veniam)
            result = someFinalComputation()
        }
    }
    return result
}

private fun someFinalComputation(): PayResult = PayResult(10.0, "OK")
