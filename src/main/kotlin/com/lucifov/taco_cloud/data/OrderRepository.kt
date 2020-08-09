package com.lucifov.taco_cloud.data

import com.lucifov.taco_cloud.models.Order
import org.springframework.data.repository.CrudRepository

interface  OrderRepository: CrudRepository<Order, Long>