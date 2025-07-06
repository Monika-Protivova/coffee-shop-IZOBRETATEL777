package com.motycka.edu.order.dao

import com.motycka.edu.OrderItemTable
import com.motycka.edu.order.dao.entity.OrderItemDTO
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

class OrderItemDAO(id: EntityID<Long>) : LongEntity(id) {
    var orderId by OrderItemTable.orderId
    var menuItemId by OrderItemTable.menuItemId


    fun toDTO(): OrderItemDTO {
        return OrderItemDTO(
            id = id.value,
            orderId = orderId,
            menuItemId = menuItemId
        )
    }
}
