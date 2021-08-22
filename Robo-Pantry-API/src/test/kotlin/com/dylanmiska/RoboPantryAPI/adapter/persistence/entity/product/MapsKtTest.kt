package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.ExpiredEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.toModel
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.domain.model.Expired
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MapsKtTest {

    val productVariantEntity = mockk<ProductVariantEntity>()
    val productVariantModel = mockk<ProductVariant>()
    val productEntity = ProductEntity(
        id = 0,
        name = "Root Beer",
        unitsOnHand = 20.0,
        unitOfMeasure = UnitOfMeasure.OUNCE,
        category = ProductCategory.BEVERAGE,
        productVariants = mutableListOf(
            productVariantEntity
        )
    )
    val productModel = Product(
        id = 0,
        name = "Root Beer",
        unitsOnHand = 20.0,
        unitOfMeasure = UnitOfMeasure.OUNCE,
        category = ProductCategory.BEVERAGE,
        productVariants = listOf(
            productVariantModel
        )
    )

    @BeforeAll
    fun init() {
        mockkStatic("com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.MapsKt")
        every { productEntity.productVariantsToModel() } returns listOf(productVariantModel)
        every { productModel.productVariantsToEntites(any<ProductEntity>()) } returns listOf(productVariantEntity)
    }

    @Test
    fun toModel() {
        val expected = productModel
        val actual = productEntity.toModel()

        assertEquals("", expected, actual)
    }

    @Test
    fun toListModel() {
        val expected = productModel.copy(productVariants = listOf())
        val actual = productEntity.toListingModel()

        assertEquals("", expected, actual)
    }

    @Test
    fun toEntity() {
        val expected = productEntity
        val actual = productModel.toEntity()

        assertEquals("", expected, actual)
    }

    @Test
    fun toEntityReferenceTest() {
        val expectedReference = productModel.toEntity()
        // test if productVariants reference the product
        verify(exactly = 1) { productModel.productVariantsToEntites(expectedReference) }
    }





}