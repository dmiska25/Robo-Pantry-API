package com.dylanmiska.RoboPantryAPI.core.domain.model

import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import java.util.*

data class Expired(
    val id: Int? = null,
    val purchase: Purchase? = null,
    val quantityExpired: Int?,
    val reason: ExpirationReason,
    val expiration: Date
)
