package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product

import com.dylanmiska.RoboPantryAPI.core.domain.model.Product

fun ProductEntity.toModel(): Product = Product(
    id = id,
    name = name,
    purchaseDate = purchaseDate,
    quantity = quantity,
    unitOfMeasure = unitOfMeasure,
    barcode = barcode
)

fun Product.toEntity(): ProductEntity = ProductEntity(
    id = id,
    name = name,
    purchaseDate = purchaseDate,
    quantity = quantity,
    unitOfMeasure = unitOfMeasure,
    barcode = barcode
)