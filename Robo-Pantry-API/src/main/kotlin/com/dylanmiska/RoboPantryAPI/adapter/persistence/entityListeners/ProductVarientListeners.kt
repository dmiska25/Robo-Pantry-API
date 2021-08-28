package com.dylanmiska.RoboPantryAPI.adapter.persistence.entityListeners

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.PurchaseDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.PostLoad
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

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

    @PrePersist
    @PreUpdate
    private fun validateChildren(productVarient: ProductVariantEntity) {
        productVarient.purchases = productVarient.purchases
            .map {
                when(it.productVariant) {
                    productVarient -> it
                    else -> it.copy(productVariant = productVarient)
                }
            }
            .map {
                when(it.product) {
                    productVarient.product -> it
                    else -> it.copy(product = productVarient.product)
                }
            }

    }
}