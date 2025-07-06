package com.motycka.edu.order.dao.repo.impl

import com.motycka.edu.config.suspendTransaction
import com.motycka.edu.order.dao.OrderDAO
import com.motycka.edu.order.dao.entity.OrderDTO
import com.motycka.edu.order.dao.entity.OrderId
import com.motycka.edu.order.dao.repo.OrderRepository

class OrderRepositoryImpl : OrderRepository {
    override suspend fun selectAll(): List<OrderDTO> = suspendTransaction {
        OrderDAO.all().map { it.toDTO() }
    }

    override suspend fun selectById(id: OrderId): OrderDTO? = suspendTransaction {
        OrderDAO.findById(id)?.toDTO()
    }

    override suspend fun create(order: OrderDTO): OrderDTO = suspendTransaction {
        OrderDAO.new {
            customerName = order.customerName
            totalAmount = order.totalAmount
            orderDate = order.orderDate
            status = order.status.name
            isPaid = order.isPaid
        }.toDTO()
    }

    override suspend fun update(order: OrderDTO): OrderDTO = suspendTransaction {
        OrderDAO.findById(order.id!!)?.apply {
            customerName = order.customerName
            totalAmount = order.totalAmount
            orderDate = order.orderDate
            status = order.status.name
            isPaid = order.isPaid
        }?.toDTO() ?: throw IllegalArgumentException("Order with id ${order.id} not found")
    }
}
