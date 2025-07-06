package com.motycka.edu.order.service

import com.motycka.edu.customer.CustomerId
import com.motycka.edu.order.dao.entity.OrderStatus
import com.motycka.edu.order.dao.entity.OrderDTO
import com.motycka.edu.order.dto.OrderRequest

interface OrderService {
    suspend fun getAllOrders(): List<OrderDTO>
    suspend fun getOrderById(id: Long): OrderDTO?
    suspend fun createOrder(request: OrderRequest, customerId: CustomerId): OrderDTO
    suspend fun updateOrderStatus(id: Long, status: OrderStatus)
}