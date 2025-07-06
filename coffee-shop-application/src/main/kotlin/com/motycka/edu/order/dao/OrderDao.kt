package com.motycka.edu.order.dao

import com.motycka.edu.OrderTable
import com.motycka.edu.order.dao.entity.OrderDTO
import com.motycka.edu.order.dao.entity.OrderStatus
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID


class OrderDAO(id: EntityID<Long>) : LongEntity(id) {
    var customerName by OrderTable.customerName
    var orderDate by OrderTable.orderDate
    var totalAmount by OrderTable.totalAmount
    var status by OrderTable.status
    var isPaid by OrderTable.isPaid

    companion object : LongEntityClass<OrderDAO>(OrderTable)

    fun toDTO(): OrderDTO {
        return OrderDTO(
            id = id.value,
            customerName = customerName,
            orderDate = orderDate,
            totalAmount = totalAmount,
            status = OrderStatus.valueOf(status),
            isPaid = isPaid,
        )
    }
}