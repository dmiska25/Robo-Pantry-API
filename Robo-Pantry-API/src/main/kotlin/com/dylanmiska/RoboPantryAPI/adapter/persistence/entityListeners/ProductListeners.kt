package com.dylanmiska.RoboPantryAPI.adapter.persistence.entityListeners

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductVariantDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.PurchaseDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.PostLoad
import javax.persistence.PrePersist
import javax.persistence.PreUpdate


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

    @PrePersist
    @PreUpdate
    private fun validateChildren(product: ProductEntity) {
        product.productVariants = product.productVariants
            .map {
                when(it.product) {
                    product -> it
                    else -> it.copy(product = product)
                }
            }
            .toMutableList()
    }


}