package com.dylanmiska.RoboPantryAPI.adapter.persistence.repository

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toModel
import com.dylanmiska.RoboPantryAPI.core.application.port.out.ProductPort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.UnitOfMeasure
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProductRepository(
    private val dao: ProductDAO
): ProductPort {

    override fun find(id: Int): Product? {
        return dao.getById(id).toModel()
    }

    override fun findAll(): List<Product> {
        return dao.findAll().map(ProductEntity::toModel)
    }

    override fun create(product: Product): Product {
        return dao.save(product.toEntity()).toModel()
    }

    override fun update(product: Product): Product {
        return dao.save(product.toEntity()).toModel()
    }

    override fun delete(id: Int): Product {
        val entity = dao.getById(id)
        dao.deleteById(id)
        return entity.toModel()
    }
}