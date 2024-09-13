package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.purchase.toResponse
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase

fun Product.toResponse(): ProductResponse = ProductResponse(
    id = id!!,
    name = name,
    category = category,
    unitOfMeasure = unitOfMeasure,
    brand = brand,
    productsOnHand = productsOnHand!!,
    unitsPerProduct = unitsPerProduct,
    barcode = barcode,
    purchases = purchases.map(Purchase::toResponse)
)

fun Product.toListResponse(): ProductListResponse = ProductListResponse(
    id = id!!,
    name = name,
    category = category,
    unitOfMeasure = unitOfMeasure,
    brand = brand,
    productsOnHand = productsOnHand!!,
    unitsPerProduct = unitsPerProduct,
    barcode = barcode
)