package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.productVariant

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.purchase.PurchaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductVariantResponse (
    val id: Int,
    val brand: String,
    @JsonProperty("products_on_hand")
    val productsOnHand: Int,
    @JsonProperty("units_per_product")
    val unitsPerProduct: Double,
    val purchases: List<PurchaseResponse>,
    val barcode: Int
)