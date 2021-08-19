package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant

import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.FindProductUseCase
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Autowired

class ProductVariantRequest(
    val id: Int?,
    val brand: String,
    @JsonProperty("units_per_product")
    val unitsPerProduct: Double,
    val barcode: Int
)