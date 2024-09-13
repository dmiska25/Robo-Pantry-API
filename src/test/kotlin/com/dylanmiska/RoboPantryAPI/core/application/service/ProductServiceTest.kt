package com.dylanmiska.RoboPantryAPI.core.application.service

import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.application.port.out.ProductPort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ProductServiceTest {

    private val gateway = mockk<ProductPort>()
    private lateinit var service: ProductService

    private val constProduct = Product(
        id = 1,
        name = "test",
        unitOfMeasure = UnitOfMeasure.UNIT,
        category = ProductCategory.BEVERAGE,
        brand = "testBrand",
        purchases = listOf(
            Purchase(
                id = 1,
                productId = 1,
                purchaseDate = Date(),
                productsPurchased = 10,
                expired = null
            )
        ),
        productsOnHand = 10,
        unitsPerProduct = 1.0,
        barcode = 123456789

    )

    private val newProduct = constProduct.copy(id = null)

    @BeforeAll
    fun setup() {
        service = ProductService(gateway)
    }

    @Test
    fun findAll() {
        every { gateway.findAll() } returns listOf(constProduct)
        val result = service.findAll()
        assertEquals("expected and actual findAll result do not match!",listOf(constProduct), result)
    }

    @Test
    fun find() {
        every { gateway.find(1) } returns constProduct
        val result = service.find(1)
        assertEquals("expected and actual find(1) result do not match!", constProduct, result)
    }

    @Test
    fun create() {
        every { gateway.create(newProduct) } returns constProduct
        val result = service.create(newProduct)
        assertEquals("expected and actual create result do not match!", Unit, result)
    }

    @Test
    fun update() {
        every { gateway.update(constProduct) } returns constProduct
        val result = service.update(constProduct)
        assertEquals("expected and actual update result do not match!", Unit, result)
    }

    @Test
    fun delete() {
        every { gateway.delete(1) } returns Unit
        val result = service.delete(1)
        assertEquals("expected and actual delete result do not match!", Unit, result)
    }
}