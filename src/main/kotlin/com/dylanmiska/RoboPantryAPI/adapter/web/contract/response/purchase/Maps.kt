package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.purchase

import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase

fun Purchase.toResponse(): PurchaseResponse = PurchaseResponse(
    id = id!!,
    purchaseDate = purchaseDate,
    productsPurchased = productsPurchased
)