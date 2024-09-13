package com.dylanmiska.RoboPantryAPI.adapter.persistence.repository

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.PurchaseDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductMapper
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseMapper
import com.dylanmiska.RoboPantryAPI.core.application.port.out.ProductPort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(
    private val dao: ProductDAO,
    private val mapper: ProductMapper,
    private val purchaseDAO: PurchaseDAO,
    private val purchaseMapper: PurchaseMapper
): ProductPort {

    override fun find(id: Int): Product? {
        return dao.findByIdOrNull(id)?.let { mapper.toModel(it) }
    }

    override fun findAll(): List<Product> {
        return mapper.toListingModel(dao.findAll())
    }

    override fun create(element: Product): Product {
        if (element.purchases.count() > 1) throw IllegalArgumentException("Saving more than one purchase is currently not supported.")
        var product = mapper.toEntity(element.copy(purchases = listOf()))
        product = dao.saveAndFlush(product)
        val purchase = element.purchases.firstOrNull()?.let { purchaseMapper.toEntity(it.copy(productId = product.id!!)) }
        purchase?.let { product.purchases = listOf(purchaseDAO.save(it)) }
        dao.flush()
        return mapper.toModel(product)
    }

    override fun update(element: Product): Product {
        return mapper.toModel(dao.save(mapper.toEntity(element)))
    }

    override fun delete(id: Int) {
        dao.deleteById(id)
    }
}