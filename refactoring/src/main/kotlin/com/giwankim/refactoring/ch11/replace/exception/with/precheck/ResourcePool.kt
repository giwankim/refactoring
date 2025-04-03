package com.giwankim.refactoring.ch11.replace.exception.with.precheck

class ResourcePool {
    private val available = ArrayDeque<Resource>()
    private val allocated = mutableListOf<Resource>()

    fun get(): Resource {
        var result: Resource
        try {
            result = available.removeFirst()
            allocated.add(result)
        } catch (e: NoSuchElementException) {
            result = Resource()
            allocated.add(result)
        }
        return result
    }
}
