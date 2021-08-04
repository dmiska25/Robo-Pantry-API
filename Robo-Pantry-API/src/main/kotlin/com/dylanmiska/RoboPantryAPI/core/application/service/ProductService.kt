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

    override fun create(product: Product): Product {
        return productGateway.create(product)
    }

    override fun update(product: Product): Product {
        return productGateway.update(product)
    }

    override fun delete(id: Int): Product {
        return productGateway.delete(id)
    }

}