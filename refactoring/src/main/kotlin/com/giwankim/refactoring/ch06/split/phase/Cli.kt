package com.giwankim.refactoring.ch06.split.phase

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import java.nio.file.Paths
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    try {
        run(args)
    } catch (e: Exception) {
        System.err.println(e)
        exitProcess(1)
    }
}

fun run(args: Array<String>) {
    require(args.isNotEmpty()) { "must supply a filename" }
    val filename = args.last()
    val input: File = Paths.get(filename).toFile()
    val mapper =
        JsonMapper
            .builder()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
            .addModule(kotlinModule())
            .build()
    val orders: List<Order> = mapper.readValue(input)
    if (args.any { it == "-r" }) {
        println(orders.count { it.status == OrderStatus.READY })
    } else {
        println(orders.size)
    }
}
