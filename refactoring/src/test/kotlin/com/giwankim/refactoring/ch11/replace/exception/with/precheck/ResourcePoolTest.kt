package com.giwankim.refactoring.ch11.replace.exception.with.precheck

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

class ResourcePoolTest :
    FunSpec({

        test("get() should return a Resource instance") {
            val resourcePool = ResourcePool()

            val result = resourcePool.get()

            result.shouldBeInstanceOf<Resource>()
        }

        test("get() should create a new Resource when pool is empty") {
            val resourcePool = ResourcePool()

            val result = resourcePool.get()

            result.shouldBeInstanceOf<Resource>()
            getAllocatedResources(resourcePool).size shouldBe 1
        }

        test("get() should create new resources on multiple calls") {
            val resourcePool = ResourcePool()

            val resource1 = resourcePool.get()
            val resource2 = resourcePool.get()

            resource1.shouldBeInstanceOf<Resource>()
            resource2.shouldBeInstanceOf<Resource>()
            getAllocatedResources(resourcePool).size shouldBe 2
        }
    })

// Helper function to access private field for testing
private fun getAllocatedResources(resourcePool: ResourcePool): List<Resource> {
    val property = ResourcePool::class.declaredMemberProperties.first { it.name == "allocated" }
    property.isAccessible = true
    return property.get(resourcePool) as List<Resource>
}
