package com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product

import com.dylanmiska.RoboPantryAPI.core.domain.model.Product

interface FindProductUseCase {
    fun findAll(): List<Product>
    fun find(id: Int): Product?
}