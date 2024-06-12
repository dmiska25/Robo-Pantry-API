package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductVariantDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PurchaseMapperTest {

    val product = mockk<Product>()
    val productEntity = mockk<ProductEntity>()
    val productVariant = mockk<ProductVariant>()
    val productVariantEntity = mockk<ProductVariantEntity>()
    val purchaseEntity = PurchaseEntity(
        id = 0,
        purchaseDate = Date(4765356735),
        productsPurchased = 2,
        expired = null,
        product = productEntity,
        productVariant = productVariantEntity
    )
    val purchaseModel = Purchase(
        id = 0,
        productId = 1,
        productVariantId = 1,
        purchaseDate = Date(4765356735),
        productsPurchased = 2,
        expired = null
    )

    private val productDAO = mockk<ProductDAO>()
    private val productVariantDAO = mockk<ProductVariantDAO>()
    private lateinit var mapper: PurchaseMapper

    @BeforeAll
    fun init() {
        mapper = PurchaseMapper(productDAO, productVariantDAO)
        every { productEntity.id } returns 1
        every { productVariantEntity.id } returns 1
        every { productDAO.findByIdOrNull(1) } returns productEntity
        every { productVariantDAO.findByIdOrNull(1) } returns productVariantEntity
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