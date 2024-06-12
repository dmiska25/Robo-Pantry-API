package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.productVariant.ProductVariantResponse
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import kotlin.reflect.KProperty

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("product")
data class ProductResponse(
    val id: Int,
    val name: String,
    val category: ProductCategory,
    @JsonProperty("units_on_hand")
    val unitsOnHand: Double,
    @JsonProperty("unit_of_measure")
    val unitOfMeasure: UnitOfMeasure,
    @JsonProperty("product_variants")
    val productVariants: List<ProductVariantResponse>
)

data class ProductListWrapperResponse(
    val products: List<ProductListResponse>
)

data class ProductListResponse(
    val id: Int,
    val name: String,
    val category: ProductCategory,
    @JsonProperty("units_on_hand")
    val unitsOnHand: Double,
    @JsonProperty("unit_of_measure")
    val unitOfMeasure: UnitOfMeasure,
)