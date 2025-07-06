package com.motycka.edu.order.dao.repo

import com.motycka.edu.order.dao.entity.OrderId
import com.motycka.edu.order.dao.entity.OrderItemDTO

interface OrderItemRepository {
    suspend fun selectByOrderId(orderId: OrderId): List<OrderItemDTO>
    suspend fun createOrderItems(orderItems: List<OrderItemDTO>)
}