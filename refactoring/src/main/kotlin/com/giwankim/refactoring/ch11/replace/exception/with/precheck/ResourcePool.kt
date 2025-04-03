package com.giwankim.refactoring.ch11.replace.exception.with.precheck

class ResourcePool {
    private val available = ArrayDeque<Resource>()
    private val allocated = mutableListOf<Resource>()

    fun get(): Resource {
        val result: Resource = if (available.isEmpty()) Resource() else available.removeFirst()
        allocated.add(result)
        return result
    }
}
