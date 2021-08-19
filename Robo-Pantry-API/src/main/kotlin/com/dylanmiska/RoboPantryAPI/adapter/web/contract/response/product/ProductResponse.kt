package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.productVariant.ProductVariantResponse
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductResponse(
    val id: Int,
    @JsonProperty("product_name")
    val name: String,
    @JsonProperty("units_on_hand")
    val unitsOnHand: Double,
    @JsonProperty("unit_of_measure")
    val unitOfMeasure: UnitOfMeasure,
    @JsonProperty("product_variants")
    val productVariants: List<ProductVariantResponse>
)

data class ProductListResponse(
    val id: Int,
    @JsonProperty("product_name")
    val name: String,
    @JsonProperty("units_on_hand")
    val unitsOnHand: Double,
    @JsonProperty("unit_of_measure")
    val unitOfMeasure: UnitOfMeasure,
)