package com.dylanmiska.RoboPantryAPI.adapter.persistence.daos

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ProductDAOTest() {

    @Autowired
    lateinit var dao: ProductDAO

    val productEntity = ProductEntity(
        id = null,
        name = "Root Beer",
        category = ProductCategory.BEVERAGE,
        unitOfMeasure = UnitOfMeasure.OUNCE,
        productVariants = mutableListOf(),
        unitsOnHand = null
    )




    @Test
    fun testCreateProduct() {
        dao.save(productEntity)



    }





}