package com.dylanmiska.RoboPantryAPI.adapter.web.controller

import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.FindProductUseCase
import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.ManageProductUseCase
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.util.AssertionErrors.assertEquals
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.text.SimpleDateFormat


@ExtendWith(SpringExtension::class)
@WebMvcTest(ProductController::class)
class ProductControllerTest(

) {
    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun find() = mockk<FindProductUseCase>()
        @Bean
        fun manage() = mockk<ManageProductUseCase>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var find: FindProductUseCase
    @Autowired
    lateinit var manage: ManageProductUseCase


    val productModel = Product(
        id = 0,
        name = "Root Beer",
        unitsOnHand = 1.0,
        unitOfMeasure = UnitOfMeasure.OUNCE,
        category = ProductCategory.BEVERAGE,
        productVariants = listOf()
    )
    val dateForm = SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy")
    val purchaseDate = dateForm.parse("May 04 19:00:00 CDT 2021")
    val newProductModel = productModel.copy(
        id = null,
        unitsOnHand = null,
        productVariants = listOf(
            ProductVariant(
                brand = "A&W",
                unitsPerProduct = 8.0,
                barcode = 456256,
                purchases = listOf(
                    Purchase(
                        purchaseDate = purchaseDate,
                        productsPurchased = 4
                    )
                )
            )
        )
    )


    @Test
    fun getProductListing() {
        /*language=json*/
        val expectedResponse = """{"products":[{"id":0,"name":"Root Beer","category":"beverage","units_on_hand":1.0,"unit_of_measure":"oz"}]}"""

        every { find.findAll() } returns listOf<Product>(productModel)

        val result = mockMvc.perform(get("/robo-pantry/products"))
            .andExpect(status().isOk)
            .andReturn()

        assertEquals("", expectedResponse , result.response.contentAsString)
    }

    @Test
    fun getProduct() {
        /*language=json*/
        val expectedResponse = """{"product":{"id":0,"name":"Root Beer","category":"beverage","units_on_hand":1.0,"unit_of_measure":"oz","product_variants":[]}}"""

        every { find.find(0) } returns productModel

        val result = mockMvc.perform(get("/robo-pantry/products/0"))
            .andExpect(status().isOk)
            .andReturn()

        assertEquals("", expectedResponse, result.response.contentAsString)
    }

    @Test
    fun createProduct() {
        val expectedResponse = ""

        every { manage.create(newProductModel) } returns Unit

        /*language=json*/
        val requestBody = """
        {
            "product": {
                "product_name": "Root Beer",
                "category": "beverage",
                "unit_of_measure": "oz"
            },
            "product_variant": {
                "brand": "A&W",
                "units_per_product": 8.0,
                "barcode": 456256
            },
            "purchase": {
                "purchase_date": "05-05-2021",
                "products_purchased": 4
            }
        }
        """.trimIndent()

        val result = mockMvc.perform(
                post("/robo-pantry/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
        )
            .andExpect(status().isCreated)
            .andReturn()

        assertEquals("", expectedResponse, result.response.contentAsString)
    }

    // TODO: Implement delete bond test
}