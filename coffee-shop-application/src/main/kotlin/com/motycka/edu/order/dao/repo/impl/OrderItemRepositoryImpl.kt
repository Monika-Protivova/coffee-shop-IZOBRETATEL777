package com.motycka.edu.order.dao.repo.impl

import com.motycka.edu.order.dao.entity.OrderId
import com.motycka.edu.order.dao.entity.OrderItemDTO
import com.motycka.edu.order.dao.repo.OrderItemRepository

class OrderItemRepositoryImpl : OrderItemRepository {
    override suspend fun selectByOrderId(orderId: OrderId): List<OrderItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun createOrderItems(orderItems: List<OrderItemDTO>) {
        TODO("Not yet implemented")
    }
}
