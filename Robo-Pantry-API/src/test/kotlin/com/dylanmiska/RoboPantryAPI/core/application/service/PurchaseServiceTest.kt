package com.dylanmiska.RoboPantryAPI.core.application.service

import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import com.dylanmiska.RoboPantryAPI.core.application.port.out.PurchasePort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Expired
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*

import java.lang.IllegalArgumentException
import java.util.*
import kotlin.NoSuchElementException

internal class PurchaseServiceTest {

    private val gateway = mockk<PurchasePort>()
    private lateinit var service: PurchaseService

    val expirationBond = Expired(
        purchaseId = 1,
        expiration = Date(34563456),
        quantityExpired = 2,
        reason = ExpirationReason.EXPIRED
    )
    val purchase = Purchase(
        id = 0,
        productId = 0,
        productVariantId = 0,
        purchaseDate = Date(34563000),
        productsPurchased = 5
    )

    @BeforeEach
    fun init() {
        service = PurchaseService(gateway)
        every { gateway.find(1) } returns purchase
        every { gateway.find(2) } returns null
        every { gateway.update(any<Purchase>()) } returns Unit
        every { gateway.create(any<Purchase>()) } returns Unit
    }

    @AfterEach
    fun post() {
        clearAllMocks()
    }

    @Test
    fun updateExpiration() {
        // invoke update
        service.updateExpiration(expirationBond)
        // purchase record should be split
        verify(exactly = 1) { gateway.update(purchase.copy(productsPurchased = 3)) }
        verify(exactly = 1) { gateway.create(purchase.copy(id = null, productsPurchased = 2, expired = expirationBond)) }
    }

    @Test
    fun invalidPurchaseIdThrowsNoSuchElement() {
        // invoke update with no purchase id
        assertThrows<NoSuchElementException> {
            service.updateExpiration(expirationBond.copy(purchaseId = null))
        }
        // invoke update with an purchase id that doesn't exist
        assertThrows<NoSuchElementException> {
            service.updateExpiration(expirationBond.copy(purchaseId = 2))
        }
    }

    @Test
    fun invalidQuantityPurchasedThrowsExceptions() {
        // invoke update with a quantityExpired>quantityPurchased
        assertThrows<IllegalArgumentException> {
            service.updateExpiration(expirationBond.copy(quantityExpired = 20))
        }
        // invoke update with quantityExpired<1
        assertThrows<IllegalArgumentException> {
            service.updateExpiration(expirationBond.copy(quantityExpired = -1))
        }
        // invoke update with expiration date < purchase date
        assertThrows<IllegalArgumentException> {
            service.updateExpiration(expirationBond.copy(expiration = Date(34563000)))
        }
    }

    @Test
    fun whenItemsExpiredEqualsQuantityPurchasedSimplyAddBond() {
        // invoke update
        service.updateExpiration(expirationBond.copy(quantityExpired = 5))
        // purchase record should have one update
        verify(exactly = 1) { gateway.update(purchase.copy(expired = expirationBond.copy(quantityExpired = 5))) }
        verify(exactly = 0) { gateway.create(any<Purchase>()) }
    }
}