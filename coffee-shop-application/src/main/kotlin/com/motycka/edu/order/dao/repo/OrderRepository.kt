package com.motycka.edu.order.dao.repo

import com.motycka.edu.order.dao.entity.OrderDTO
import com.motycka.edu.order.dao.entity.OrderId

interface OrderRepository {

    suspend fun selectAll(): List<OrderDTO>

    suspend fun selectById(id: OrderId): OrderDTO?

    suspend fun create(order: OrderDTO): OrderDTO

    suspend fun update(order: OrderDTO): OrderDTO

}