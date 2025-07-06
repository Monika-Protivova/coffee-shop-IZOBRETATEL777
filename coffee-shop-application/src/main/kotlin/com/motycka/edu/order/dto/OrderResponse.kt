package com.motycka.edu.order.dto

import com.motycka.edu.order.dao.entity.OrderId
import com.motycka.edu.order.dao.entity.OrderStatus
import kotlinx.serialization.Serializable

@Serializable
data class OrderResponse(
    val id: OrderId,
    val menuItems: List<OrderItemResponse>,
    val totalPrice: Double,
    val status: OrderStatus,
)