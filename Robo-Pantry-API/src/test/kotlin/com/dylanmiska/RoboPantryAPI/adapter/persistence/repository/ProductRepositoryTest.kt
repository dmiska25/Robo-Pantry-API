package com.dylanmiska.RoboPantryAPI.adapter.persistence.repository

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toEntity
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.UnitOfMeasure
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.springframework.test.util.AssertionErrors.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ProductRepositoryTest {

    private val dao = mockk<ProductDAO>()
    private lateinit var repository: ProductRepository

    @BeforeAll
    fun setup() {
        repository = ProductRepository(dao)
    }

    private val constProduct = Product(
        id = 1,
        name = "test",
        purchaseDate = Date(1627855592),
        quantity = 1.0,
        unitOfMeasure = UnitOfMeasure.UNIT,
        barcode = 12345
    )
    private val constNewProduct = constProduct.copy(id = null)
    private val constProductEntity = constProduct.toEntity()
    private val constNewProductEntity = constProductEntity.copy(id = null)



    @Test
    fun find() {
        every { dao.getById(1) } returns constProductEntity
        val result = repository.find(1)
        assertEquals("expected and actual find result do not match!", constProduct, result)
    }

    @Test
    fun findAll() {
        every { dao.findAll() } returns listOf(constProductEntity)
        val result = repository.findAll()
        assertEquals("expected and actual findAll result do not match!", listOf(constProduct), result)
    }

    @Test
    fun create() {
        every { dao.save(constNewProductEntity) } returns constProductEntity
        val result = repository.create(constNewProduct)
        assertEquals("expected and actual create result do not match!", constProduct, result)
    }

    @Test
    fun update() {
        every { dao.save(constProductEntity) } returns constProductEntity
        val result = repository.update(constProduct)
        assertEquals("expected and actual update result do not match!", constProduct, result)
    }

    @Test
    fun delete() {
        every { dao.getById(1) } returns constProductEntity
        every { dao.deleteById(1) } returns Unit
        val result = repository.delete(1)
        assertEquals("expected and actual delete result do not match!", constProduct, result)
    }
}