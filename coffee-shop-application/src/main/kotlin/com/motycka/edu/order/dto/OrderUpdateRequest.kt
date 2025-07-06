package com.motycka.edu.order.dto

import com.motycka.edu.order.dao.entity.OrderStatus
import kotlinx.serialization.Serializable

@Serializable
data class OrderUpdateRequest(
    val status: OrderStatus
)