package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantMapper
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.test.util.AssertionErrors.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductMapperTest {

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

    private val productVariantMapper = mockk<ProductVariantMapper>()
    private lateinit var mapper: ProductMapper

    @BeforeAll
    fun init() {
        mapper = ProductMapper(productVariantMapper)
        every { productVariantMapper.toModel(productVariantEntity) } returns productVariantModel
        every { productVariantMapper.listToEntityList(listOf(productVariantModel), any<ProductEntity>()) } returns listOf(productVariantEntity)
    }

    @Test
    fun toModel() {
        val expected = productModel
        val actual = mapper.toModel(productEntity)

        assertEquals("", expected, actual)
    }

    @Test
    fun toListingModel() {
        val expected = listOf(productModel.copy(productVariants = listOf()))
        val actual = mapper.toListingModel(listOf(productEntity))

        assertEquals("", expected, actual)
    }

    @Test
    fun toEntity() {
        val expected = productEntity
        val actual = mapper.toEntity(productModel)

        assertEquals("", expected, actual)
    }

    @Test
    fun toEntityReferenceTest() {
        // test if productVariants reference the product
        val slot = slot<ProductEntity>()
        every { productVariantMapper.listToEntityList(listOf(productVariantModel), capture(slot)) } returns listOf(productVariantEntity)
        val productEntityReference = mapper.toEntity(productModel)
        assertEquals("Reference is inconsistent", productEntityReference, slot.captured)
    }





}