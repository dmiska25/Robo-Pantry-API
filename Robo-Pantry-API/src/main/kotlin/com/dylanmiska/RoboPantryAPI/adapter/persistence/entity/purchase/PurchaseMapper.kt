package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductVariantDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.toModel
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class PurchaseMapper(private val productDAO: ProductDAO, private val productVariantDAO: ProductVariantDAO) {

    fun toEntity(purchase: Purchase): PurchaseEntity = PurchaseEntity(
        id = purchase.id,
        product = productDAO.findByIdOrNull(purchase.productId) ?:
            throw NoSuchElementException("No Such product with id: ${purchase.productId}"),
        productVariant = productVariantDAO.findByIdOrNull(purchase.productVariantId) ?:
            throw NoSuchElementException("No such productVariant with id: ${purchase.productVariantId}"),
        purchaseDate = purchase.purchaseDate,
        productsPurchased = purchase.productsPurchased,
        expired = purchase.expired?.toEntity()
    )

    fun listToEntityList(purchases: List<Purchase>, productVariantEntity: ProductVariantEntity, productEntity: ProductEntity): List<PurchaseEntity> {

        return purchases.map {
            PurchaseEntity(
                it.id,
                productEntity,
                productVariantEntity,
                it.purchaseDate,
                it.productsPurchased,
                it.expired?.toEntity()
            )
        }
    }

    fun toModel(purchaseEntity: PurchaseEntity): Purchase = Purchase(
        id = purchaseEntity.id,
        productId = purchaseEntity.product.id!!,
        productVariantId = purchaseEntity.productVariant.id!!,
        purchaseDate = purchaseEntity.purchaseDate,
        productsPurchased = purchaseEntity.productsPurchased,
        expired = purchaseEntity.expired?.toModel()
    )
}