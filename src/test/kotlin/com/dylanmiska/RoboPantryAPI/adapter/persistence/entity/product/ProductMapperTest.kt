package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseMapper
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.test.util.AssertionErrors.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductMapperTest {
    val purchase = mockk<Purchase>()
    val purchaseEntity = mockk<PurchaseEntity>()
    val product = Product(
        id = 0,
        name = "Root Beer",
        category = ProductCategory.BEVERAGE,
        unitOfMeasure = UnitOfMeasure.OUNCE,
        brand = "A&W",
        productsOnHand = 2,
        unitsPerProduct = 8.0,
        barcode = 452346,
        purchases = mutableListOf(
            purchase
        )
    )
    val productEntity = ProductEntity(
        id = 0,
        name = "Root Beer",
        category = ProductCategory.BEVERAGE,
        unitOfMeasure = UnitOfMeasure.OUNCE,
        brand = "A&W",
        unitsPerProduct = 8.0,
        barcode = 452346,
        purchases = mutableListOf(
            purchaseEntity
        )
    )

    private val productDAO = mockk<ProductDAO>()
    private val purchaseMapper = mockk<PurchaseMapper>()
    private lateinit var mapper: ProductMapper

    @BeforeAll
    fun init() {
        mapper = ProductMapper(purchaseMapper)
        every { purchaseMapper.listToModelList(listOf(purchaseEntity)) } returns listOf(purchase)
        every { purchaseMapper.listToEntityList(listOf(purchase),any<ProductEntity>()) } returns listOf(purchaseEntity)
        every { purchaseEntity.productsPurchased } returns 2
    }

    @Test
    fun toModel() {
        val expected = product
        val actual = mapper.toModel(this.productEntity)

        assertEquals("", expected, actual)
    }

    @Test
    fun toEntity() {
        val expected = this.productEntity
        val actual = mapper.toEntity(product)

        assertEquals("", expected, actual)
    }

    @Test
    fun toEntityReferenceTest() {
        // test if purchases reference the product
        val slot = slot<ProductEntity>()
        every { purchaseMapper.listToEntityList(listOf(purchase),capture(slot)) } returns listOf(purchaseEntity)
        val productReference = mapper.toEntity(product)
        assertEquals("Reference is inconsistent", productReference, slot.captured)
    }

}