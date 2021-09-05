//package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase
//
//import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
//import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toEntity
//import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toModel
//import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
//import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.toEntity
//import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.toModel
//import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
//import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
//import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
//import io.mockk.MockK
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.mockkObject
//import org.junit.jupiter.api.BeforeAll
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.TestInstance
//import org.springframework.test.util.AssertionErrors
//import org.springframework.test.util.AssertionErrors.assertEquals
//import java.util.*
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class MapsKtTest {
//
//    val product = mockk<Product>()
//    val productEntity = mockk<ProductEntity>()
//    val productVariant = mockk<ProductVariant>()
//    val productVariantEntity = mockk<ProductVariantEntity>()
//    val purchaseEntity = PurchaseEntity(
//        id = 0,
//        purchaseDate = Date(4765356735),
//        productsPurchased = 2,
//        expired = null,
//        product = productEntity,
//        productVariant = productVariantEntity
//    )
//    val purchaseModel = Purchase(
//        id = 0,
//        purchaseDate = Date(4765356735),
//        productsPurchased = 2,
//        expired = null
//    )
//
//    @BeforeAll
//    fun mockingLogic() {
//        every { product.toEntity() } returns productEntity
//        every { productEntity.toModel() } returns product
//        every { productVariant.toEntity(productEntity) } returns productVariantEntity
//        every { productVariantEntity.toModel() } returns productVariant
//    }
//
//    @Test
//    fun toModel() {
//        val expected = purchaseModel
//        val actual = purchaseEntity.toModel()
//
//        assertEquals("to model expected doesn't match", expected, actual)
//    }
//
//    @Test
//    fun toEntity() {
//        val expected = purchaseEntity
//        val actual = purchaseModel.toEntity(productEntity, productVariantEntity)
//
//        assertEquals("to model expected doesn't match", expected, actual)
//    }
//}