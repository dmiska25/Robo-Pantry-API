package com.dylanmiska.RoboPantryAPI.adapter.persistence.repository

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductMapper
import com.dylanmiska.RoboPantryAPI.core.application.port.out.ProductPort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import org.springframework.stereotype.Repository

@Repository
open class ProductRepository(
    private val dao: ProductDAO,
    private val mapper: ProductMapper
): ProductPort {

    override fun find(id: Int): Product? {
        return mapper.toModel(dao.getById(id))
    }

    override fun findAll(): List<Product> {
        return mapper.toListingModel(dao.findAll())
    }

    override fun create(element: Product) {
        val product = mapper.toEntity(element)
        dao.save(product)
    }

    override fun update(element: Product) {
        dao.save(mapper.toEntity(element))
    }

    override fun delete(id: Int) {
        dao.deleteById(id)
    }
}