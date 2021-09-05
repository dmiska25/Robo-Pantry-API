package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant

import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import io.mockk.every
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals
import kotlin.math.exp

internal class MapsKtTest {
    val productVariantRequest = ProductVariantRequest(
        id = 1,
        brand = "A&W",
        unitsPerProduct = 2.0,
        barcode = 45636543
    )
    val productVariantModel = ProductVariant(
        id = 1,
        brand = "A&W",
        unitsPerProduct = 2.0,
        barcode = 45636543,
        purchases = listOf()
    )

    @Test
    fun toModel() {
        val expected = productVariantModel
        val actual = productVariantRequest.toModel(listOf())
        assertEquals("productVariantRequest to model not equal", expected, actual)
    }
}