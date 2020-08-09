package com.lucifov.taco_cloud.data

import com.lucifov.taco_cloud.models.Order

interface OrderRepository {
    fun save(order: Order): Order
}