package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant

import com.fasterxml.jackson.annotation.JsonProperty

class ProductVariantRequest(
    val id: Int?,
    val brand: String,
    @JsonProperty("units_per_product")
    val unitsPerProduct: Double,
    val barcode: Int
)