package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant.ProductVariantRequest
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase.PurchaseRequest
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductRequest(
    val id: Int?,
    @JsonProperty("product_name")
    val name: String,
    val category: ProductCategory,
    @JsonProperty("unit_of_measure")
    val unitOfMeasure: UnitOfMeasure
)

data class EmbeddedProductRequest(
    val product: ProductRequest,
    @JsonProperty("product_variant")
    val productVariant: ProductVariantRequest,
    val purchase: PurchaseRequest
)
