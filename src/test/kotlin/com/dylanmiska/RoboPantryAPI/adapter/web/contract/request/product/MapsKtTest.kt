package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product

import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals

internal class MapsKtTest {
    val productRequest = ProductRequest(
        id = 1,
        name = "Root Beer",
        category = ProductCategory.BEVERAGE,
        unitOfMeasure = UnitOfMeasure.OUNCE,
        brand = "A&W",
        unitsPerProduct = 2.0,
        barcode = 45636543
    )
    val productModel = Product(
        id = 1,
        name = "Root Beer",
        category = ProductCategory.BEVERAGE,
        unitOfMeasure = UnitOfMeasure.OUNCE,
        brand = "A&W",
        unitsPerProduct = 2.0,
        barcode = 45636543,
        purchases = listOf()
    )

    @Test
    fun toModel() {
        val expected = productModel
        val actual = productRequest.toModel(listOf())
        assertEquals("productRequest to model not equal", expected, actual)
    }
}