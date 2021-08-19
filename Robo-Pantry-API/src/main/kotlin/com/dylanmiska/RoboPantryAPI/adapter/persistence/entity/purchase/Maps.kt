package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.toModel
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase

fun PurchaseEntity.toModel(): Purchase = Purchase(
    id = id,
    purchaseDate = purchaseDate,
    productsPurchased = productsPurchased,
    expired = expired?.toModel()
)

fun Purchase.toEntity(product: ProductEntity, productVariant: ProductVariantEntity): PurchaseEntity = PurchaseEntity(
    id = id,
    product = product,
    productVariant = productVariant,
    purchaseDate = purchaseDate,
    productsPurchased = productsPurchased,
    expired = expired?.toEntity()
)