package com.giwankim.refactoring.chapter04

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

fun sampleProvinceData() = Json.decodeFromString<ProvinceData>(provinceJson)
