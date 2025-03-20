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
        println(run(args))
    } catch (e: Exception) {
        System.err.println(e)
        exitProcess(1)
    }
}

fun run(args: Array<String>): Int {
    require(args.isNotEmpty()) { "must supply a filename" }
    val onlyCountReady = args.any { it == "-r" }
    val commandLine = CommandLine(onlyCountReady)
    val filename = args.last()
    return countOrders(commandLine, args, filename)
}

private fun countOrders(
    commandLine: CommandLine,
    args: Array<String>,
    filename: String,
): Int {
    val input: File = Paths.get(filename).toFile()
    val mapper =
        JsonMapper
            .builder()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
            .addModule(kotlinModule())
            .build()
    val orders: List<Order> = mapper.readValue(input)
    return if (commandLine.onlyCountReady) {
        orders.count { it.status == OrderStatus.READY }
    } else {
        orders.size
    }
}

data class CommandLine(
    val onlyCountReady: Boolean,
)
