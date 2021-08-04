package com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product

import com.dylanmiska.RoboPantryAPI.core.domain.model.Product

interface ManageProductUseCase {
    fun create(product: Product): Product
    fun update(product: Product): Product
    fun delete(id: Int): Product
}