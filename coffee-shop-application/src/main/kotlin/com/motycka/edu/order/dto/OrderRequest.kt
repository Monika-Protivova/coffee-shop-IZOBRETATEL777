package com.motycka.edu.order.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderRequest(
    val items: List<OrderItemRequest>
)