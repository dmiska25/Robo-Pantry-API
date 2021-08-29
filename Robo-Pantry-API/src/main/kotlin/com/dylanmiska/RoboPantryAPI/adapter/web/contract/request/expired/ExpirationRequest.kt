package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.expired

import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ExpirationRequest(
    @JsonProperty("purchase_id")
    val purchaseId: Int,
    @JsonProperty("quantity_expired")
    val quantityExpired: Int,
    val reason: ExpirationReason,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val expiration: Date
)
