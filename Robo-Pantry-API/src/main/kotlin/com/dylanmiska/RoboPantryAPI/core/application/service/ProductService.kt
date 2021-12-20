package com.dylanmiska.RoboPantryAPI.core.application.service

import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.FindProductUseCase
import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.ManageProductUseCase
import com.dylanmiska.RoboPantryAPI.core.application.port.out.ProductPort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import org.springframework.stereotype.Service

@Service
class ProductService(
        private val productGateway: ProductPort
): FindProductUseCase, ManageProductUseCase {
    override fun findAll(): List<Product> {
        return productGateway.findAll()
    }

    override fun find(id: Int): Product? {
        return productGateway.find(id)
    }

    override fun create(element: Product) {
        productGateway.create(element)
    }

    override fun update(element: Product) {
        productGateway.update(element)
    }

    override fun delete(id: Int) {
        productGateway.delete(id)
    }

}