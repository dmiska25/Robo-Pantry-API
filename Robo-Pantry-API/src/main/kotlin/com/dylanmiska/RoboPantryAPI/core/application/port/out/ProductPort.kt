package com.dylanmiska.RoboPantryAPI.core.application.port.out

import com.dylanmiska.RoboPantryAPI.core.domain.model.Product

interface ProductPort {
    fun find(id: Int): Product?
    fun findAll(): List<Product>
    fun create(product: Product): Product
    fun update(product: Product): Product
    fun delete(id: Int): Product
}