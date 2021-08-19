package com.dylanmiska.RoboPantryAPI.core.domain.model

import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure

data class ProductVariant(
    val id: Int? = null,
    val brand: String,
    val purchases: List<Purchase>,
    val productsOnHand: Int? = null,
    val unitsPerProduct: Double,
    val barcode: Int
)
