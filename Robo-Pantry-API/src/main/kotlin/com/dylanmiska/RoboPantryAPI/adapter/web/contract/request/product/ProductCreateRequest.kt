package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product

import com.dylanmiska.RoboPantryAPI.core.domain.model.UnitOfMeasure
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ProductCreateRequest(
    @JsonProperty("product_name")
    val name: String,
    @JsonProperty("purchase_date")
    val purchaseDate: Date,
    val quantity: Double,
    @JsonProperty("unit_of_measure")
    val unitOfMeasure: UnitOfMeasure,
    val barcode: Long
)