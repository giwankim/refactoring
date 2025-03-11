package com.giwankim.refactoring.ch08.split.loop

fun process(people: List<Person>): String {
    var youngest = if (people[0] != null) people[0].age else Int.MAX_VALUE
    var totalSalary = 0
    for (p in people) {
        totalSalary += p.salary
    }

    for (p in people) {
        if (p.age < youngest) {
            youngest = p.age
        }
    }

    return "youngestAge: $youngest, total salary: $totalSalary"
}
