package com.dylanmiska.RoboPantryAPI.adapter.persistence.repository

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toListingModel
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toModel
import com.dylanmiska.RoboPantryAPI.core.application.port.out.ProductPort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(
    private val dao: ProductDAO
): ProductPort {

    override fun find(id: Int): Product? {
        val product = dao.getById(id)
        return product.toModel()
//        return dao.getById(id).toModel()
    }

    override fun findAll(): List<Product> {
        return dao.findAll().map(ProductEntity::toListingModel)
    }

    override fun create(element: Product) {
        dao.save(element.toEntity())
    }

    override fun update(element: Product) {
        dao.save(element.toEntity())
    }

    override fun delete(id: Int) {
        dao.deleteById(id)
    }
}