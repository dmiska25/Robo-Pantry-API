package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase.toModel
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase

fun ProductRequest.toModel(purchases: List<Purchase>): Product = Product(
    id = id,
    name = name,
    category = category,
    unitOfMeasure = unitOfMeasure,
    brand = brand,
    unitsPerProduct = unitsPerProduct,
    barcode = barcode,
    purchases = purchases
)

fun EmbeddedProductRequest.toModel(): Product =
    product.toModel(listOf(
        purchase.toModel()
    ))
