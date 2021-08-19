package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.productVariant.toResponse
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant

fun Product.toResponse(): ProductResponse = ProductResponse(
    id = id!!,
    name = name,
    unitsOnHand = unitsOnHand!!,
    unitOfMeasure = unitOfMeasure,
    productVariants = productVariants.map(ProductVariant::toResponse)
)

fun Product.toListResponse(): ProductListResponse = ProductListResponse(
    id = id!!,
    name = name,
    unitsOnHand = unitsOnHand!!,
    unitOfMeasure = unitOfMeasure
)