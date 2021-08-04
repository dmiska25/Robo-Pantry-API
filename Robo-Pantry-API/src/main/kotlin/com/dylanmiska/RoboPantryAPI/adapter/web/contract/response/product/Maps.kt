package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product

import com.dylanmiska.RoboPantryAPI.core.domain.model.Product

fun Product.toResponse(): ProductResponse = ProductResponse(
    id = id!!,
    name = name,
    purchaseDate = purchaseDate,
    quantity = quantity,
    unitOfMeasure = unitOfMeasure,
    barcode = barcode
)