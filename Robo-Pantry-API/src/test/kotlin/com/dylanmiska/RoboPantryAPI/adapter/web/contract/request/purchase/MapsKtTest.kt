package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase

import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*

internal class MapsKtTest {
    val purchaseRequest = PurchaseRequest(
        purchaseDate = Date(234634643),
        productsPurchased = 2
    )
    val purchaseModel = Purchase(
        purchaseDate = Date(234634643),
        productsPurchased = 2
    )

    @Test
    fun toModel() {
        val expected = purchaseModel
        val actual = purchaseRequest.toModel()
        assertEquals("purchaseRequest to model not equal", expected, actual)
    }
}