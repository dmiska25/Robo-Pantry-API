package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.expired

import com.dylanmiska.RoboPantryAPI.core.domain.model.Expired

fun ExpirationRequest.toModel(): Expired = Expired(
    purchaseId = purchaseId,
    quantityExpired = quantityExpired,
    reason = reason,
    expiration = expiration
)