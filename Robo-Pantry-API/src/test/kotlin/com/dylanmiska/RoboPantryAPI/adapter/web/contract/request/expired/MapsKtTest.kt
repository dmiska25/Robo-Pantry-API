package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.expired

import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import com.dylanmiska.RoboPantryAPI.core.domain.model.Expired
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*

internal class MapsKtTest {

    val expirationRequest = ExpirationRequest(
        purchaseId = 1,
        quantityExpired = 2,
        reason = ExpirationReason.EXPIRED,
        expiration = Date(5343457345)
    )
    val expirationModel = Expired(
        purchaseId = 1,
        quantityExpired = 2,
        reason = ExpirationReason.EXPIRED,
        expiration = Date(5343457345)
    )

    @Test
    fun toModel() {
        val expected = expirationModel
        val actual = expirationRequest.toModel()
        assertEquals("", expected, actual)
    }
}