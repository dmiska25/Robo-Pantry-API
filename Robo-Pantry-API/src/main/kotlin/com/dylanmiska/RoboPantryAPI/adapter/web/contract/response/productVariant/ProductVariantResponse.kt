package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.productVariant

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.purchase.PurchaseResponse
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.fasterxml.jackson.annotation.JsonProperty

class ProductVariantResponse (
    val id: Int,
    val brand: String,
    @JsonProperty("products_on_hand")
    val productsOnHand: Int,
    val purchases: List<PurchaseResponse>,
    val barcode: Int
)