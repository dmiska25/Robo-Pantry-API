package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseMapper
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.test.util.AssertionErrors.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductVariantMapperTest {
    val product = mockk<Product>()
    val productEntity = mockk<ProductEntity>()
    val purchase = mockk<Purchase>()
    val purchaseEntity = mockk<PurchaseEntity>()
    val productVariant = ProductVariant(
        id = 0,
        productId = 1,
        brand = "A&W",
        productsOnHand = 2,
        unitsPerProduct = 8.0,
        barcode = 452346,
        purchases = mutableListOf(
            purchase
        )
    )
    val productVariantEntity = ProductVariantEntity(
        id = 0,
        brand = "A&W",
        product = productEntity,
        productsOnHand = 2,
        unitsPerProduct = 8.0,
        barcode = 452346,
        purchases = mutableListOf(
            purchaseEntity
        )
    )

    private val productDAO = mockk<ProductDAO>()
    private val purchaseMapper = mockk<PurchaseMapper>()
    private lateinit var mapper: ProductVariantMapper

    @BeforeAll
    fun init() {
        mapper = ProductVariantMapper(productDAO, purchaseMapper)
        every { purchaseMapper.listToModelList(listOf(purchaseEntity)) } returns listOf(purchase)
        every { productEntity.id } returns 1
        every { purchaseMapper.listToEntityList(listOf(purchase),any<ProductVariantEntity>(),productEntity) } returns listOf(purchaseEntity)
    }

    @Test
    fun toModel() {
        val expected = productVariant
        val actual = mapper.toModel(productVariantEntity)

        assertEquals("", expected, actual)
    }

    @Test
    fun toEntity() {
        val expected = productVariantEntity
        val actual = mapper.toEntity(productVariant, productEntity)

        assertEquals("", expected, actual)
    }

    @Test
    fun toEntityReferenceTest() {
        // test if purchases reference the productVariant
        val slot = slot<ProductVariantEntity>()
        every { purchaseMapper.listToEntityList(listOf(purchase),capture(slot),productEntity) } returns listOf(purchaseEntity)
        val productVariantReference = mapper.toEntity(productVariant, productEntity)
        assertEquals("Reference is inconsistent", productVariantReference, slot.captured)
    }

}