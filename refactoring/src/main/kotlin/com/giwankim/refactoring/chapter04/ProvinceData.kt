package com.giwankim.refactoring.chapter04

import kotlinx.serialization.Serializable

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
