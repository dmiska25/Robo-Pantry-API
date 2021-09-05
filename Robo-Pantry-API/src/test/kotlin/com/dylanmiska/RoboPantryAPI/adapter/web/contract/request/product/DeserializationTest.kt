package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.generics.BaseDeserializationTest
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import org.springframework.boot.test.autoconfigure.json.JsonTest

@JsonTest
internal class DeserializationTest: BaseDeserializationTest<ProductRequest>(ProductRequest::class.java) {
    /*language=json*/
    override var jsonObject = """
        {
          "id": 1,
          "product_name": "Root Beer",
          "category": "BEVERAGE",
          "unit_of_measure": "oz"
        }
    """.trimIndent()
    override var expected = ProductRequest(
        id = 1,
        name = "Root Beer",
        category = ProductCategory.BEVERAGE,
        unitOfMeasure = UnitOfMeasure.OUNCE
    )
}