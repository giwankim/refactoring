package com.giwankim.refactoring.ch08.split.loop

fun process(people: List<Person>): String {
    fun totalSalary(): Int {
        var totalSalary = 0
        for (p in people) {
            totalSalary += p.salary
        }
        return totalSalary
    }

    fun youngestAge(): Int {
        var youngest = if (people[0] != null) people[0].age else Int.MAX_VALUE
        for (p in people) {
            if (p.age < youngest) {
                youngest = p.age
            }
        }
        return youngest
    }

    return "youngestAge: ${youngestAge()}, total salary: ${totalSalary()}"
}
