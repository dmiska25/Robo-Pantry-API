package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PurchaseMapperTest {

    val productId = 1234
    val productEntity = mockk<ProductEntity>()
    val purchaseEntity = PurchaseEntity(
        id = 0,
        purchaseDate = Date(4765356735),
        productsPurchased = 2,
        expired = null,
        productId = productId,
    )
    val purchaseModel = Purchase(
        id = 0,
        productId = productId,
        purchaseDate = Date(4765356735),
        productsPurchased = 2,
        expired = null
    )

    private lateinit var mapper: PurchaseMapper

    @BeforeAll
    fun init() {
        mapper = PurchaseMapper()
        every { this@PurchaseMapperTest.productEntity.id } returns productId
    }

    @Test
    fun toModel() {
        val expected = purchaseModel
        val actual = mapper.toModel(purchaseEntity)

        assertEquals("to model expected doesn't match", expected, actual)
    }

    // TODO: Add listing test cases

    @Test
    fun toEntity() {
        val expected = purchaseEntity
        val actual = mapper.toEntity(purchaseModel)

        assertEquals("to model expected doesn't match", expected, actual)
    }

    // TODO: Add failure checks
}