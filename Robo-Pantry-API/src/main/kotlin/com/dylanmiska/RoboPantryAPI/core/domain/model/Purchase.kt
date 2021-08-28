package com.dylanmiska.RoboPantryAPI.core.domain.model

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import java.util.*

data class Purchase(
    val id: Int? = null,
    val product: Product? = null,
    val productVariant: ProductVariant? = null,
    val purchaseDate: Date,
    val productsPurchased: Int,
    val expired: Expired? = null
)
