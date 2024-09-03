package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.purchase.PurchaseResponse
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductResponse @JsonCreator constructor(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("category") val category: ProductCategory,
    @JsonProperty("unit_of_measure") val unitOfMeasure: UnitOfMeasure,
    @JsonProperty("brand") val brand: String,
    @JsonProperty("products_on_hand") val productsOnHand: Int,
    @JsonProperty("units_per_product") val unitsPerProduct: Double,
    @JsonProperty("purchases") val purchases: List<PurchaseResponse>,
    @JsonProperty("barcode") val barcode: Int
)

data class ProductListWrapperResponse @JsonCreator constructor(
    @JsonProperty("products") val products: List<ProductListResponse>
)

data class ProductListResponse @JsonCreator constructor(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("category") val category: ProductCategory,
    @JsonProperty("unit_of_measure") val unitOfMeasure: UnitOfMeasure,
    @JsonProperty("brand") val brand: String,
    @JsonProperty("products_on_hand") val productsOnHand: Int,
    @JsonProperty("units_per_product") val unitsPerProduct: Double,
    @JsonProperty("barcode") val barcode: Int
)
