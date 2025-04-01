package com.giwankim.refactoring.ch11.remove.flag.argument

import java.time.LocalDateTime

fun deliveryDate(
    order: Order,
    isRush: Boolean,
): LocalDateTime =
    if (isRush) {
        var deliveryTime: Long
        if (order.deliveryState in listOf("MA", "CT")) {
            deliveryTime = 1L
        } else if (order.deliveryState in listOf("NY", "NJ")) {
            deliveryTime = 2L
        } else {
            deliveryTime = 3L
        }
        order.placedOn.plusDays(1 + deliveryTime)
    } else {
        var deliveryTime: Long
        if (order.deliveryState in listOf("MA", "CT", "NY")) {
            deliveryTime = 2L
        } else if (order.deliveryState in listOf("ME", "NH")) {
            deliveryTime = 3L
        } else {
            deliveryTime = 4L
        }
        order.placedOn.plusDays(2 + deliveryTime)
    }
