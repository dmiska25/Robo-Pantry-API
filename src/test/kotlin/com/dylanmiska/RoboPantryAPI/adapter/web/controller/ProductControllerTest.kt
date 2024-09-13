package com.dylanmiska.RoboPantryAPI.adapter.web.controller

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product.ProductListResponse
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product.ProductListWrapperResponse
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product.ProductResponse
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.purchase.PurchaseResponse
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.FindProductUseCase
import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.ManageProductUseCase
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
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
class ProductControllerTest {
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

    val objectMapper = ObjectMapper()

    val productModel = Product(
        id = 0,
        name = "Root Beer",
        unitOfMeasure = UnitOfMeasure.OUNCE,
        category = ProductCategory.BEVERAGE,
        brand = "A&W",
        unitsPerProduct = 8.0,
        barcode = 456256,
        productsOnHand = 2,
        purchases = listOf(
            Purchase(
                id = 1,
                productId = 2,
                purchaseDate = SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy").parse("May 04 19:00:00 CDT 2021"),
                productsPurchased = 8,
                expired = null
            )
        )
    )

    @Test
    fun getProductListing() {
        val expectedResponse = ProductListWrapperResponse(
            products = listOf(
                ProductListResponse(
                    id = 0,
                    name = "Root Beer",
                    category = ProductCategory.BEVERAGE,
                    unitOfMeasure = UnitOfMeasure.OUNCE,
                    brand = "A&W",
                    productsOnHand = 2,
                    unitsPerProduct = 8.0,
                    barcode = 456256
                )
            )
        )

        every { find.findAll() } returns listOf(productModel)

        val result = mockMvc.perform(get("/robo-pantry/products"))
            .andExpect(status().isOk)
            .andReturn()

        val actualResponse = objectMapper.readValue<ProductListWrapperResponse>(result.response.contentAsString)

        assertEquals(
            "response does not match expected",
            expectedResponse,
            actualResponse
        )
    }

    @Test
    fun getProduct() {
        val expectedResponse = ProductResponse(
            id = 0,
            name = "Root Beer",
            category = ProductCategory.BEVERAGE,
            unitOfMeasure = UnitOfMeasure.OUNCE,
            brand = "A&W",
            productsOnHand = 2,
            unitsPerProduct = 8.0,
            barcode = 456256,
            purchases = listOf(
                PurchaseResponse(
                    id = 1,
                    purchaseDate = SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy").parse("May 04 19:00:00 CDT 2021"),
                    productsPurchased = 8
                )
            )
        )

        every { find.find(0) } returns productModel

        val result = mockMvc.perform(get("/robo-pantry/products/0"))
            .andExpect(status().isOk)
            .andReturn()

        val actualResponse = objectMapper.readValue<ProductResponse>(result.response.contentAsString)

        assertEquals("response does not match expected", expectedResponse, actualResponse)
    }

    @Test
    fun createProduct() {
        val expectedResponse = ""

        every { manage.create(productModel.copy(id = null, productsOnHand = null, purchases = listOf(productModel.purchases.first().copy(id = null, productId = null)))
        ) } returns Unit

        /*language=json*/
        val requestBody = """
        {
            "product": {
                "name": "Root Beer",
                "category": "beverage",
                "unit_of_measure": "oz",
                "brand": "A&W",
                "units_per_product": 8.0,
                "barcode": 456256
            },
            "purchase": {
                "purchase_date": "05-05-2021",
                "products_purchased": 8
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
}