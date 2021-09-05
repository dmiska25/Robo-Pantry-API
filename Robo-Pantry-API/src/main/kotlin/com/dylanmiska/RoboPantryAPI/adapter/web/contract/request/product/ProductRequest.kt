package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant.ProductVariantRequest
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase.PurchaseRequest
import com.dylanmiska.RoboPantryAPI.common.constants.ILLEGAL_CHARACTERS
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.fasterxml.jackson.annotation.JsonProperty
import org.valiktor.functions.*
import org.valiktor.validate

data class ProductRequest(
    @JsonProperty("id")
    val id: Int?,
    @JsonProperty("product_name")
    val name: String,
    @JsonProperty("category")
    val category: ProductCategory,
    @JsonProperty("unit_of_measure")
    val unitOfMeasure: UnitOfMeasure
) {
    init {
        validate(this) {
            validate(ProductRequest::id).isGreaterThan(-1)
            validate(ProductRequest::name).isNotNull().isNotBlank().isNotEmpty().doesNotContainAny(ILLEGAL_CHARACTERS)
            validate(ProductRequest::category).isNotNull()
            validate(ProductRequest::unitOfMeasure).isNotNull()
        }
    }
}

data class EmbeddedProductRequest(
    @JsonProperty("product")
    val product: ProductRequest,
    @JsonProperty("product_variant")
    val productVariant: ProductVariantRequest,
    @JsonProperty("purchase")
    val purchase: PurchaseRequest
) {
    init {
        // TODO: Verifiy ids are acurate
        validate(this) {
            validate(EmbeddedProductRequest::product).isNotNull()
            validate(EmbeddedProductRequest::productVariant).isNotNull()
            validate(EmbeddedProductRequest::purchase).isNotNull()
        }
    }
}
