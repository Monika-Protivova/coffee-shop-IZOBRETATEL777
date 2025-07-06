package com.motycka.edu.order.dao.entity

import com.motycka.edu.order.dao.OrderItemId
import java.time.LocalDateTime

typealias OrderId = Long

enum class OrderStatus {
    PENDING,
    PAID,
    COMPLETED,
    CANCELLED
}

data class OrderDTO(
    val id: OrderId?,
    val customerName: String,
    val orderDate: LocalDateTime,
    val totalAmount: Double,
    val status: OrderStatus,
    val isPaid: Boolean
)

data class OrderItemDTO(
    val id: OrderItemId?,
    val orderId: Long,
    val menuItemId: Long,
)