package com.dylanmiska.RoboPantryAPI.adapter.web.controller

import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.FindProductUseCase
import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.ManageProductUseCase
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.UnitOfMeasure
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

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



    @Test
    fun getProductListing() {

        val expectedResponse =
            "[{\"id\":1,\"name\":\"test\",\"purchaseDate\":\"1970-01-19T20:10:55.592+00:00\"," +
                    "\"quantity\":1.0,\"unitOfMeasure\":\"UNIT\",\"barcode\":12345}]"

        every { find.findAll() } returns listOf<Product>(
            Product(
                id = 1,
                name = "test",
                purchaseDate = Date(1627855592),
                quantity = 1.0,
                unitOfMeasure = UnitOfMeasure.UNIT,
                barcode = 12345
            )
        )

        val result = mockMvc.perform(get("/robo-pantry/products"))
            .andExpect(status().isOk)
            .andReturn()

        assertEquals("", expectedResponse , result.response.contentAsString)
    }

    @Test
    fun getProduct() {

        val expectedResponse =
            "{\"id\":1,\"name\":\"test\",\"purchaseDate\":\"1970-01-19T20:10:55.592+00:00\"," +
                    "\"quantity\":1.0,\"unitOfMeasure\":\"UNIT\",\"barcode\":12345}"

        every { find.find(0) } returns Product(
            id = 1,
            name = "test",
            purchaseDate = Date(1627855592),
            quantity = 1.0,
            unitOfMeasure = UnitOfMeasure.UNIT,
            barcode = 12345
        )

        val result = mockMvc.perform(get("/robo-pantry/products/0"))
            .andExpect(status().isOk)
            .andReturn()

        assertEquals("", expectedResponse, result.response.contentAsString)
    }

    @Test
    fun createProduct() {
        val expectedResponse = "{\"id\":0,\"name\":\"test\",\"purchaseDate\":\"1970-01-19T20:10:55.592+00:00\"," +
                "\"quantity\":1.0,\"unitOfMeasure\":\"UNIT\",\"barcode\":12345}"

        val newProduct = Product(
            name = "test",
            purchaseDate = Date(1627855592),
            quantity = 1.0,
            unitOfMeasure = UnitOfMeasure.UNIT,
            barcode = 12345
        )

        every { manage.create(newProduct) } returns newProduct.copy(id = 0)

        /*language=json*/
        val requestBody = """
            {
                "name":"test",
                "purchaseDate":"1970-01-19T20:10:55.592+00:00",
                "quantity":1.0,
                "unitOfMeasure":"UNIT",
                "barcode":12345
            }
        """.trimIndent()

        val result = mockMvc.perform(
                post("/robo-pantry/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
        )
            .andExpect(status().isOk)
            .andReturn()

        assertEquals("", expectedResponse, result.response.contentAsString)
    }

    @Test
    fun updateProduct() {
        val expectedResponse = "{\"id\":0,\"name\":\"test\",\"purchaseDate\":\"1970-01-19T20:10:55.592+00:00\"," +
                "\"quantity\":1.0,\"unitOfMeasure\":\"UNIT\",\"barcode\":12345}"

        val replaceProduct = Product(
            id = 0,
            name = "test",
            purchaseDate = Date(1627855592),
            quantity = 1.0,
            unitOfMeasure = UnitOfMeasure.UNIT,
            barcode = 12345
        )

        every { manage.update(replaceProduct) } returns replaceProduct

        /*language=json*/
        val requestBody = """
            {
                "id": 0,
                "name":"test",
                "purchaseDate":"1970-01-19T20:10:55.592+00:00",
                "quantity":1.0,
                "unitOfMeasure":"UNIT",
                "barcode":12345
            }
        """.trimIndent()

        val result = mockMvc.perform(
            put("/robo-pantry/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isOk)
            .andReturn()

        assertEquals("", expectedResponse, result.response.contentAsString)
    }

    @Test
    fun deleteProduct() {
        val expectedResponse = "{\"id\":0,\"name\":\"test\",\"purchaseDate\":\"1970-01-19T20:10:55.592+00:00\"," +
                "\"quantity\":1.0,\"unitOfMeasure\":\"UNIT\",\"barcode\":12345}"

        val deleteProduct = Product(
            id = 0,
            name = "test",
            purchaseDate = Date(1627855592),
            quantity = 1.0,
            unitOfMeasure = UnitOfMeasure.UNIT,
            barcode = 12345
        )

        every { manage.delete(0) } returns deleteProduct

        val result = mockMvc.perform(delete("/robo-pantry/products/0"))
            .andExpect(status().isOk)
            .andReturn()

        assertEquals("", expectedResponse, result.response.contentAsString)
    }

}