package com.giwankim.refactoring.ch11.replace.exception.with.precheck

class ResourcePool {
    private val available = ArrayDeque<Resource>()
    private val allocated = mutableListOf<Resource>()

    fun get(): Resource {
        var result: Resource
        if (available.isEmpty()) {
            result = Resource()
            allocated.add(result)
        } else {
            try {
                result = available.removeFirst()
                allocated.add(result)
            } catch (e: NoSuchElementException) {
                throw error("unreachable")
            }
        }
        return result
    }
}
