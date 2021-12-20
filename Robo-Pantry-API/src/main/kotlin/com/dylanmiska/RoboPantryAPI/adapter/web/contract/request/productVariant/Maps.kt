package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant

import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase

fun ProductVariantRequest.toModel(purchases: List<Purchase>): ProductVariant = ProductVariant(
    id = id,
    brand = brand,
    unitsPerProduct = unitsPerProduct,
    barcode = barcode,
    purchases = purchases
)