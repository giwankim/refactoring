package com.giwankim.refactoring.chapter04

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private val provinceJson =
    """
    {
      "name": "Asia",
      "producers": [
        {"name": "Byzantium", "cost": 10, "production": 9},
        {"name": "Attalia",   "cost": 12, "production": 10},
        {"name": "Sinope",    "cost": 10, "production": 6}
      ],
      "demand": 30,
      "price": 20
    }
    """.trimIndent()

@Serializable
data class ProvinceData(
    val name: String,
    val producers: List<ProducerData>,
    val demand: Int,
    val price: Int,
)

@Serializable
data class ProducerData(
    val name: String,
    val cost: Int,
    val production: Int,
)

private val json = Json { ignoreUnknownKeys = true }

fun sampleProvinceData() = json.decodeFromString<ProvinceData>(provinceJson)
