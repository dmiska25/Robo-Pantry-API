package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant

import com.dylanmiska.RoboPantryAPI.common.constants.ILLEGAL_CHARACTERS
import com.fasterxml.jackson.annotation.JsonProperty
import org.valiktor.functions.*
import org.valiktor.validate

data class ProductVariantRequest(
    @JsonProperty("id")
    val id: Int?,
    @JsonProperty("brand")
    val brand: String,
    @JsonProperty("units_per_product")
    val unitsPerProduct: Double,
    @JsonProperty("barcode")
    val barcode: Int
) {
    init {
        validate(this) {
            validate(ProductVariantRequest::id).isGreaterThan(-1)
            validate(ProductVariantRequest::brand).isNotBlank().isNotEmpty().doesNotContainAny(ILLEGAL_CHARACTERS)
            validate(ProductVariantRequest::unitsPerProduct).isNotNull().isGreaterThan(0.0)
            validate(ProductVariantRequest::barcode).isNotNull().isGreaterThan(0)
        }
    }
}