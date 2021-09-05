package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.generics.BaseDeserializationTest
import org.springframework.boot.test.autoconfigure.json.JsonTest

@JsonTest
internal class DeserializationTest: BaseDeserializationTest<PurchaseRequest>(PurchaseRequest::class.java) {
    /*language=json*/
    override var jsonObject = """
        {
          "purchase_date": "23-08-2021",
          "products_purchased": "2"
        }
    """.trimIndent()
    override var expected = PurchaseRequest(
        purchaseDate = dateFormat.parse("23-08-2021"),
        productsPurchased = 2
    )
}