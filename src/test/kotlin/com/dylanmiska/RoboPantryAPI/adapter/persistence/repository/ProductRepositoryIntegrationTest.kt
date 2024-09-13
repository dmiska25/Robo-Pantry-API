package com.dylanmiska.RoboPantryAPI.adapter.persistence.repository

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.PurchaseDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductMapper
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.setup.IntegrationTestSuite
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.util.AssertionErrors.assertEquals
import java.time.Instant.now
import java.util.*

@Transactional
internal class ProductRepositoryIntegrationTest : IntegrationTestSuite() {
    @Autowired
    private lateinit var dao: ProductDAO

    @Autowired
    private lateinit var purchaseDao: PurchaseDAO

    @Autowired
    private lateinit var repository: ProductRepository

    @Autowired
    private lateinit var productMapper: ProductMapper

    private val constProduct = Product(
        id = 1,
        name = "test",
        unitOfMeasure = UnitOfMeasure.UNIT,
        category = ProductCategory.BEVERAGE,
        brand = "testBrand",
        purchases = listOf(),
        productsOnHand = 0,
        unitsPerProduct = 1.0,
        barcode = 1

    )
    private val constNewProduct = constProduct.copy(id = null)
    private val unitResponse = Unit



    @Test
    fun find() {
        val entity = dao.save(productMapper.toEntity(constProduct.copy(id = null)))
        val expected = constProduct.copy(id = entity.id)

        val result = repository.find(entity.id!!)
        assertEquals("expected and actual find result do not match!", expected, result)
    }

    @Test
    fun find_returns_purchases_and_sums_them() {
        val entity = dao.save(productMapper.toEntity(constProduct.copy(id = null)))
        entity.purchases = listOf(purchaseDao.save(PurchaseEntity(id = null, productId = entity.id!!, productsPurchased = 2, purchaseDate = Date.from(now()), expired = null)))
        val expected = productMapper.toModel(entity)

        val result = repository.find(entity.id!!)
        assertEquals("expected and actual find result do not match!", expected.purchases.first(), result?.purchases?.first())
        assertEquals("sum isn't expected!", 2, result?.productsOnHand)
    }

    @Test
    fun findAll() {
        val entity = dao.save(productMapper.toEntity(constProduct.copy(id = null)))
        val expected = constProduct.copy(id = entity.id)

        val result = repository.findAll()
        assertEquals("expected and actual findAll result do not match!", listOf(expected), result)
    }

    @Test
    fun create() {
        val result = repository.create(constNewProduct)
        val count = dao.count()
        val expected = constNewProduct.copy(id = result.id)

        assertEquals("expected and actual create result do not match!", expected, result)
        assertEquals("counts not right", 1L, count)
    }

    @Test
    fun update() {
        val entity = dao.save(productMapper.toEntity(constProduct.copy(id = null)))
        val modified = productMapper.toModel(entity.copy(name = "test2"))

        val result = repository.update(modified)
        assertEquals("expected and actual update result do not match!", modified, result)

        val modifiedResult = dao.findAll().also { assert(it.size == 1) }.first()
        assertEquals("modification was not made!", "test2", modifiedResult.name)
    }

    @Test
    fun delete() {
        val entity = dao.save(productMapper.toEntity(constProduct.copy(id = null)))
        val result = repository.delete(entity.id!!)
        assertEquals("expected and actual delete result do not match!", unitResponse, result)

        val count = dao.count()
        assertEquals("counts not right!", 0L, count)
    }
}
