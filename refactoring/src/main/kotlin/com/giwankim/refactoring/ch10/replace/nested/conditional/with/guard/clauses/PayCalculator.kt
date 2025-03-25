package com.giwankim.refactoring.ch10.replace.nested.conditional.with.guard.clauses

fun payAmount(employee: Employee): PayResult {
    var result: PayResult
    if (employee.isSeparated) {
        return PayResult(0.0, "SEP")
    }
    if (employee.isRetired) {
        return PayResult(0.0, "RET")
    }
    // logic to compute amount
    // lorem.ipsum(dolor.sitAmet)
    // consectetur(adipiscing).elit()
    // sed.do.eiusmod = tempor.incididunt.ut(labore) && dolore(magna.aliqua)
    // ut.enim.ad(minim.veniam)
    result = someFinalComputation()
    return result
}

private fun someFinalComputation(): PayResult = PayResult(10.0, "OK")
