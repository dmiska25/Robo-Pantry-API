package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired


import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import com.dylanmiska.RoboPantryAPI.core.domain.model.Expired
import org.junit.jupiter.api.Test
import org.springframework.test.util.AssertionErrors.assertEquals
import java.util.*

class MapsKtTest {

    val expiredEntity = ExpiredEntity(
        id = 0,
        reason = ExpirationReason.EXPIRED,
        expiration = Date(35467354673)
    )
    val expiredModel = Expired(
        id = 0,
        reason = ExpirationReason.EXPIRED,
        quantityExpired = 5,
        expiration = Date(35467354673)
    )

    @Test
    fun toModel() {
        val expected = expiredModel
        val actual = expiredEntity.toModel(5)

        assertEquals("to model expected doesn't match", expected, actual)
    }

    @Test
    fun toEntity() {
        val expected = expiredEntity.copy(id = null)
        val actual = expiredModel.copy(id = null).toEntity()

        assertEquals("to entity expected doesn't match", expected, actual)
    }
}