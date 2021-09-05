package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant.ProductVariantRequest
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant.toModel
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase.PurchaseRequest
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase.toModel
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.test.util.AssertionErrors.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MapsKtTest {

    val purchaseRequest = mockk<PurchaseRequest>()
    val purchaseModel = mockk<Purchase>()
    val productVariantRequest = ProductVariantRequest(
        id = 2,
        barcode = 5463456,
        unitsPerProduct = 2.0,
        brand = "A&W"
    )
    val productVariantModel = ProductVariant(
        id = 2,
        barcode = 5463456,
        unitsPerProduct = 2.0,
        brand = "A&W",
        purchases = listOf(purchaseModel)
    )
    val productRequest = ProductRequest(
        id = 1,
        name = "Root Beer",
        category = ProductCategory.BEVERAGE,
        unitOfMeasure = UnitOfMeasure.OUNCE
    )
    val productModel = Product(
        id = 1,
        name = "Root Beer",
        category = ProductCategory.BEVERAGE,
        unitOfMeasure =  UnitOfMeasure.OUNCE,
        productVariants = listOf(productVariantModel)
    )
    val embeddedProductRequest = EmbeddedProductRequest(
        productRequest,productVariantRequest,purchaseRequest
    )


    @BeforeAll
    fun init() {
        mockkStatic("com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase.MapsKt")
        every { purchaseRequest.toModel() } returns purchaseModel
    }


    @Test
    fun toModel() {
        val expected = productModel.copy(productVariants = listOf())
        val actual = productRequest.toModel(listOf())
        assertEquals("productRequest to product not equal", expected, actual)
    }

    @Test
    fun embeddedToModel() {
        val expected = productModel
        val actual = embeddedProductRequest.toModel()
        assertEquals("embeddedProduceRequest to product not equal", expected, actual)
    }
}