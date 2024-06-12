package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.generics.BaseDeserializationTest
import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.autoconfigure.json.JsonTest

@JsonTest
internal class DeserializationTest: BaseDeserializationTest<ProductVariantRequest>(ProductVariantRequest::class.java) {
    /*language=json*/
    override var jsonObject = """
        {
          "id": 1,
          "brand": "A&W",
          "units_per_product": 2.0,
          "barcode": 54663262
        }
    """.trimIndent()
    override var expected = ProductVariantRequest(
        id = 1,
        brand = "A&W",
        unitsPerProduct = 2.0,
        barcode = 54663262
    )

    @Test
    fun testDoubleDeserialization() {
        jsonObject = jsonObject.replace("product", "products")
        assertThrows<ValueInstantiationException> {
            getActual()
        }
    }


}