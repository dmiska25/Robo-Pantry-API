package com.dylanmiska.RoboPantryAPI.adapter.persistence.entityListeners

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.PurchaseDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.PostLoad


@Component
class ProductListeners() {
    companion object{
        private lateinit var purchaseDAO: PurchaseDAO
    }

    @Autowired
    fun init(purDAO: PurchaseDAO) {
        purchaseDAO = purDAO
    }

    @PostLoad
    private fun countQuantity(product: ProductEntity) {
        product.unitsOnHand = purchaseDAO.sumUnitsByProduct(product)
    }


}