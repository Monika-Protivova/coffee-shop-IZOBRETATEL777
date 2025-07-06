package com.motycka.edu.order.service

import com.motycka.edu.order.dao.entity.OrderItemDTO
import com.motycka.edu.menu.MenuItemDTO
import com.motycka.edu.menu.MenuRepository
import java.math.BigDecimal
import java.math.RoundingMode

object PriceCalculator {

    suspend fun calculatePrice(
        menuItems: List<MenuItemDTO>,
        discountInPercent: Double,
        orderItems: List<OrderItemDTO> = emptyList(),
        menuItemRepository: MenuRepository
    ): Double {
        val priceMap = menuItems.associateBy { it.id }

        val originalPrice = orderItems.sumOf { orderItem ->
            val price = priceMap[orderItem.menuItemId]?.price ?: 0.0
            (price * orderItem.orderId * (menuItemRepository.selectMenuItemById(orderItem.menuItemId)?.price ?: 0.0))
        }

        val validDiscount = discountInPercent.coerceIn(0.0, 100.0)
        val discountMultiplier = 1.0 - (validDiscount / 100.0)

        val finalPrice = originalPrice * discountMultiplier

        return BigDecimal(finalPrice).setScale(2, RoundingMode.HALF_UP).toDouble()
    }
}