package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.expired

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.generics.BaseDeserializationTest
import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import org.springframework.boot.test.autoconfigure.json.JsonTest

@JsonTest
internal class DeserializationTest : BaseDeserializationTest<ExpirationRequest>(ExpirationRequest::class.java) {
    /*language=json*/
    override var jsonObject = """
        {
          "purchase_id": 1,
          "quantity_expired": 2,
          "reason": "EXPIRED",
          "expiration": "22-08-2021"
        }
    """.trimIndent()
    override var expected = ExpirationRequest(
        purchaseId = 1,
        quantityExpired = 2,
        reason = ExpirationReason.EXPIRED,
        expiration = dateFormat.parse("22-08-2021")
    )



}