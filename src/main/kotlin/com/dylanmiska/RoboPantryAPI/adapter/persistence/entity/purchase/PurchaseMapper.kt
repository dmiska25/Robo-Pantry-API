package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.toModel
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import org.springframework.stereotype.Component

@Component
class PurchaseMapper {

    fun toEntity(purchase: Purchase): PurchaseEntity = PurchaseEntity(
        id = purchase.id,
        productId = purchase.productId ?: throw IllegalArgumentException("Product ID cannot be null"),
        purchaseDate = purchase.purchaseDate,
        productsPurchased = purchase.productsPurchased,
        expired = purchase.expired?.toEntity()
    )

    fun listToEntityList(purchases: List<Purchase>, productEntity: ProductEntity): List<PurchaseEntity> = purchases.map(::toEntity)

    fun toModel(purchaseEntity: PurchaseEntity): Purchase = Purchase(
        id = purchaseEntity.id,
        productId = purchaseEntity.productId,
        purchaseDate = purchaseEntity.purchaseDate,
        productsPurchased = purchaseEntity.productsPurchased,
        expired = purchaseEntity.expired?.toModel(purchaseEntity.productsPurchased)
    )

    fun listToModelList(purchases: List<PurchaseEntity>): List<Purchase> =
        purchases.map {
            this.toModel(it)
        }
}
