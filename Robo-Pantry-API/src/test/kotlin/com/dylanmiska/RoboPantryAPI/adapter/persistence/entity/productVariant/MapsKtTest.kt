package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.toModel
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.aspectj.weaver.patterns.ConcreteCflowPointcut
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.springframework.test.util.AssertionErrors.assertEquals
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MapsKtTest {
    val product = mockk<Product>()
    val productEntity = mockk<ProductEntity>()
    val purchase = mockk<Purchase>()
    val purchaseEntity = mockk<PurchaseEntity>()
    val productVariant = ProductVariant(
        id = 0,
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

    @BeforeAll
    fun init() {
        mockkStatic("com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.MapsKt")
        every { purchase.toEntity(productEntity, productVariantEntity) } returns purchaseEntity
        every { productVariant.purchasesToEntities(productEntity, any<ProductVariantEntity>()) } returns listOf(purchaseEntity)
        every { productVariantEntity.purchasesToModel() } returns listOf(purchase)
    }

    @Test
    fun toModel() {
        val expected = productVariant
        val actual = productVariantEntity.toModel()

        assertEquals("", expected, actual)
    }

    @Test
    fun toEntity() {
        val expected = productVariantEntity
        val actual = productVariant.toEntity(productEntity)

        assertEquals("", expected, actual)
    }

    @Test
    fun toEntityReferenceTest() {
        val expectedReference = productVariant.toEntity(productEntity)
        // test if purchases reference the productVariant
        verify(exactly = 1) { productVariant.purchasesToEntities(productEntity, expectedReference) }
    }

}