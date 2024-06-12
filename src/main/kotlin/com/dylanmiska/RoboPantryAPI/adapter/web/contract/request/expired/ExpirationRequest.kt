package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.expired

import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.valiktor.functions.isGreaterThan
import org.valiktor.functions.isLessThan
import org.valiktor.functions.isNotNull
import org.valiktor.validate
import java.util.*

data class ExpirationRequest(
    @JsonProperty("purchase_id")
    val purchaseId: Int,
    @JsonProperty("quantity_expired")
    val quantityExpired: Int,
    @JsonProperty("reason")
    val reason: ExpirationReason,
    @JsonProperty("expiration")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val expiration: Date
) {
    init {
        validate(this) {
            validate(ExpirationRequest::purchaseId).isNotNull()
            validate(ExpirationRequest::quantityExpired).isNotNull().isGreaterThan(0)
            validate(ExpirationRequest::reason).isNotNull()
            validate(ExpirationRequest::expiration).isNotNull().isLessThan(Date())
        }
    }
}
