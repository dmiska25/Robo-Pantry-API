package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.toModel
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toModel
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.toModel
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase

fun PurchaseEntity.toModel(): Purchase = Purchase(
    id = id,
    product = product?.toModel(),
    productVariant = productVariant?.toModel(),
    purchaseDate = purchaseDate,
    productsPurchased = productsPurchased,
    expired = expired?.toModel()
)

fun Purchase.toEntity(): PurchaseEntity = PurchaseEntity(
    id = id,
    product = product?.toEntity(),
    productVariant = productVariant?.toEntity(),
    purchaseDate = purchaseDate,
    productsPurchased = productsPurchased,
    expired = expired?.toEntity()
)