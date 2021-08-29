package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.expired

import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import com.dylanmiska.RoboPantryAPI.core.domain.model.Expired
import java.util.*

fun ExpirationRequest.toModel(): Expired = Expired(
    purchaseId = purchaseId,
    quantityExpired = quantityExpired,
    reason = reason,
    expiration = expiration
)