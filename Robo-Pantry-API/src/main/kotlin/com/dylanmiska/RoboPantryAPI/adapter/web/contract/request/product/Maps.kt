package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product

import com.dylanmiska.RoboPantryAPI.core.domain.model.Product

fun ProductUpdateRequest.toModel(): Product = Product(
    id = id,
    name = name,
    purchaseDate = purchaseDate,
    quantity = quantity,
    unitOfMeasure = unitOfMeasure,
    barcode = barcode
)

fun ProductCreateRequest.toModel(): Product = Product(
    name = name,
    purchaseDate = purchaseDate,
    quantity = quantity,
    unitOfMeasure = unitOfMeasure,
    barcode = barcode
)