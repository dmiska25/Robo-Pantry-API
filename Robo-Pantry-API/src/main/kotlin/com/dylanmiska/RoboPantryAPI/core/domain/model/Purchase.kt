package com.dylanmiska.RoboPantryAPI.core.domain.model

import java.util.*

data class Purchase(
    val id: Int? = null,
    val productId: Int? = null,
    val productVariantId: Int? = null,
    val purchaseDate: Date,
    val productsPurchased: Int,
    val expired: Expired? = null
)
