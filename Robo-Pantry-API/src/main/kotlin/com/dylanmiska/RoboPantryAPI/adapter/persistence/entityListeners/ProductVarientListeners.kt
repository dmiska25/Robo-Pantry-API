package com.dylanmiska.RoboPantryAPI.adapter.persistence.entityListeners

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.PurchaseDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.PostLoad

@Component
class ProductVarientListeners() {

    companion object{
        private lateinit var purchaseDAO: PurchaseDAO
    }

    @Autowired
    fun init(DAO: PurchaseDAO) {
        purchaseDAO = DAO
    }

    @PostLoad
    private fun countQuantity(productVarient: ProductVariantEntity) {
        productVarient.productsOnHand = purchaseDAO.sumProductsByProductVariant(productVarient)
    }
}