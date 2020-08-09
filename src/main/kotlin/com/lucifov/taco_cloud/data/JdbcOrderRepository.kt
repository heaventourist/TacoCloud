package com.lucifov.taco_cloud.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.lucifov.taco_cloud.models.Order
import com.lucifov.taco_cloud.models.Taco
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Repository
class JdbcOrderRepository(@Autowired val jdbc: JdbcTemplate) : OrderRepository {
    private val orderInserter: SimpleJdbcInsert = SimpleJdbcInsert(jdbc)
            .withTableName("Taco_Order")
            .usingGeneratedKeyColumns("id")

    private val orderTacoInserter: SimpleJdbcInsert = SimpleJdbcInsert(jdbc)
            .withTableName("Taco_Order_Tacos")
    private val objectMapper = ObjectMapper()
    override fun save(order: Order): Order {
        val orderId = saveOrderDetails(order)
        order.designs.forEach { taco ->
            saveTacoToOrder(taco, orderId)
        }
        order.id = orderId
        return order
    }

    private fun saveOrderDetails(order: Order): Long {
        val map = mapOf("id" to order.id, "deliveryName" to order.name, "deliveryStreet" to order.street,
        "deliveryCity" to order.city, "deliveryState" to order.state, "deliveryZip" to order.zip,
        "ccNumber" to order.ccNumber, "ccExpiration" to order.ccExpiration, "ccCVV" to order.ccCVV,
        "placedAt" to Timestamp(order.placedAt.time))
        return orderInserter.executeAndReturnKey(map).toLong()
    }

    private fun saveTacoToOrder(taco: Taco, orderId: Long) {
        val map = mapOf("tacoOrder" to orderId, "taco" to taco.id)
        orderTacoInserter.execute(map)
    }
}