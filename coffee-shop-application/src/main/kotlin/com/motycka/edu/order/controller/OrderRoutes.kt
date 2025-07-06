package com.motycka.edu.order.controller

import com.motycka.edu.order.dto.OrderRequest
import com.motycka.edu.order.service.OrderService
import com.motycka.edu.order.dto.OrderUpdateRequest
import com.motycka.edu.security.IdentityDTO
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.authentication
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val logger = KotlinLogging.logger {}

private const val ORDER_NOT_FOUND = "Order not found"
private const val INVALID_ID = "Invalid ID format"

/**
 * Registers routes for managing orders.
 * Requires JWT authentication and roles: STAFF or CUSTOMER.
 */


fun Route.orderRoutes(
    orderService: OrderService,
    basePath: String
) {
        route("$basePath/orders") {

            // GET /orders
            get {
                logger.debug { "GET orders" }
                val responses = orderService.getAllOrders()
                logger.debug { responses.toString() }
                call.respond(HttpStatusCode.OK, responses)
            }

            // GET /orders/{id}
            get("{id}") {
                val id = call.parameters["id"]?.toLongOrNullOrRespond() ?: return@get
                orderService.getOrderById(id)
                    ?: call.respond(HttpStatusCode.NotFound, ORDER_NOT_FOUND)
            }

            // POST /orders
            post {

                val user = call.getUserIdentity()

                val request = call.receive<OrderRequest>();
                val created = orderService.createOrder(request, user.customerId)
                call.respond(HttpStatusCode.Created, created)
            }

            // PUT /orders/{id}
            put("{id}") {
                val id = call.parameters["id"]?.toLongOrNullOrRespond() ?: return@put
                val updateReq = call.receive<OrderUpdateRequest>();

                orderService.updateOrderStatus(id, updateReq.status)
            }
        }
}


private fun ApplicationCall.getUserIdentity(): IdentityDTO {
    return this.authentication.principal<IdentityDTO>()
        ?: throw BadRequestException("User identity not found in request")
}


private fun String.toLongOrNullOrRespond(): Long? {
    return this.toLongOrNull().also {
        if (it == null) throw BadRequestException(INVALID_ID)
    }
}
