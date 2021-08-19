package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase

import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase

fun PurchaseRequest.toModel(): Purchase = Purchase(
    purchaseDate = purchaseDate,
    productsPurchased = productsPurchased
)