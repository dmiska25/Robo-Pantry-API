package com.dylanmiska.RoboPantryAPI.core.domain.model

data class ProductVariant(
    val id: Int? = null,
    val productId: Int? = null,
    val brand: String,
    val purchases: List<Purchase>,
    val productsOnHand: Int? = null,
    val unitsPerProduct: Double,
    val barcode: Int
)
