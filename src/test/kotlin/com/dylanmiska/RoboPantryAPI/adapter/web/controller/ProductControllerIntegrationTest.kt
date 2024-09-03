package com.dylanmiska.RoboPantryAPI.adapter.web.controller

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product.ProductListResponse
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product.ProductListWrapperResponse
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product.ProductResponse
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.purchase.PurchaseResponse
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import com.dylanmiska.RoboPantryAPI.setup.IntegrationTestSuite
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.transaction.Transactional
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.text.SimpleDateFormat

@Transactional
class ProductControllerIntegrationTest : IntegrationTestSuite() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var productDAO: ProductDAO

    val objectMapper = ObjectMapper()

    @Test
    fun getProductListing() {
        val productEntity = setupData()
        val expected = ProductListResponse(
            id = productEntity.id!!,
             name = productEntity.name,
             category = productEntity.category,
             unitOfMeasure = productEntity.unitOfMeasure,
             brand = productEntity.brand,
             productsOnHand = productEntity.getProductsOnHand(),
             unitsPerProduct = productEntity.unitsPerProduct,
             barcode = productEntity.barcode
        )

        val result = mockMvc.perform(get("/robo-pantry/products"))
            .andExpect(status().isOk)
            .andReturn()

        val actualResponse = objectMapper.readValue<ProductListWrapperResponse>(result.response.contentAsString)
            .products
            .first()

        assertEquals(
            "response does not match expected",
            expected,
            actualResponse
        )
    }

    @Test
    fun getProduct() {
        val productEntity = setupData()
        val expected = ProductResponse(
            id = productEntity.id!!,
            name = productEntity.name,
            category = productEntity.category,
            unitOfMeasure = productEntity.unitOfMeasure,
            brand = productEntity.brand,
            productsOnHand = productEntity.getProductsOnHand(),
            unitsPerProduct = productEntity.unitsPerProduct,
            barcode = productEntity.barcode,
            purchases = listOf(PurchaseResponse(
                id = productEntity.purchases.first().id!!,
                purchaseDate = productEntity.purchases.first().purchaseDate,
                productsPurchased = productEntity.purchases.first().productsPurchased
            ))
        )

        val result = mockMvc.perform(get("/robo-pantry/products/${productEntity.id!!}"))
            .andExpect(status().isOk)
            .andReturn()

        val actualResponse = objectMapper.readValue<ProductResponse>(result.response.contentAsString)

        assertEquals("response does not match expected", expected, actualResponse)
    }

    @Test
    fun createProduct() {
        val expectedResponse = ""

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

        val all = productDAO.findAll()
        assertEquals(1, all.count())
        val saved = all.first()
        assertEquals("Root Beer", saved.name)
        assertEquals(ProductCategory.BEVERAGE, saved.category)
        assertEquals(UnitOfMeasure.OUNCE, saved.unitOfMeasure)
        assertEquals("A&W", saved.brand)
        assertEquals(8.0, saved.unitsPerProduct)
        assertEquals(456256, saved.barcode)
        assertEquals("05-04-2021", SimpleDateFormat("MM-dd-yyyy").format(saved.purchases.first().purchaseDate))
        assertEquals(8, saved.purchases.first().productsPurchased)
    }

    fun setupData(): ProductEntity {
        var product = ProductEntity(
            id = null,
            name = "Root Beer",
            unitOfMeasure = UnitOfMeasure.OUNCE,
            category = ProductCategory.BEVERAGE,
            brand = "A&W",
            unitsPerProduct = 8.0,
            barcode = 456256,
            purchases = listOf()
        )
        product = productDAO.save(product)
        product.purchases = listOf(
            PurchaseEntity(
                id = null,
                productId = product.id!!,
                purchaseDate = SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy").parse("May 04 19:00:00 CDT 2021"),
                productsPurchased = 8,
                expired = null
            )
        )
        productDAO.flush()

        return product
    }
}