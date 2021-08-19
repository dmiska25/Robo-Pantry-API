package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.productVariant

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.purchase.toResponse
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase

fun ProductVariant.toResponse(): ProductVariantResponse = ProductVariantResponse(
    id = id!!,
    brand = brand,
    productsOnHand = productsOnHand!!,
    barcode = barcode,
    purchases = purchases.map(Purchase::toResponse)
)