package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase.PurchaseRequest
import com.dylanmiska.RoboPantryAPI.common.constants.ILLEGAL_CHARACTERS
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.fasterxml.jackson.annotation.JsonProperty
import org.valiktor.functions.*
import org.valiktor.validate

data class ProductRequest(
    @JsonProperty("id")
    val id: Int? = null,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("category")
    val category: ProductCategory,
    @JsonProperty("unit_of_measure")
    val unitOfMeasure: UnitOfMeasure,
    @JsonProperty("brand")
    val brand: String,
    @JsonProperty("units_per_product")
    val unitsPerProduct: Double,
    @JsonProperty("barcode")
    val barcode: Int
) {
    init {
        validate(this) {
            validate(ProductRequest::id).isGreaterThan(-1)
            validate(ProductRequest::name).isNotNull().isNotBlank().isNotEmpty().doesNotContainAny(ILLEGAL_CHARACTERS)
            validate(ProductRequest::category).isNotNull()
            validate(ProductRequest::unitOfMeasure).isNotNull()
            validate(ProductRequest::brand).isNotBlank().isNotEmpty().doesNotContainAny(ILLEGAL_CHARACTERS)
            validate(ProductRequest::unitsPerProduct).isNotNull().isGreaterThan(0.0)
            validate(ProductRequest::barcode).isNotNull().isGreaterThan(0)
        }
    }
}

data class EmbeddedProductRequest(
    @JsonProperty("product")
    val product: ProductRequest,
    @JsonProperty("purchase")
    val purchase: PurchaseRequest
) {
    init {
        // TODO: Verifiy ids are acurate
        validate(this) {
            validate(EmbeddedProductRequest::product).isNotNull()
            validate(EmbeddedProductRequest::purchase).isNotNull()
        }
    }
}
