package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toModel
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.toModel
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase

fun ProductVariantEntity.toModel(): ProductVariant = ProductVariant(
    id = id,
    product = product?.toModel(),
    brand = brand,
    productsOnHand = productsOnHand,
    unitsPerProduct = unitsPerProduct,
    barcode = barcode,
    purchases = purchases.map(PurchaseEntity::toModel)
)

fun ProductVariant.toEntity(): ProductVariantEntity  = ProductVariantEntity(
        id = id,
        brand = brand,
        product = product?.toEntity(),
        productsOnHand = productsOnHand,
        unitsPerProduct = unitsPerProduct,
        barcode = barcode,
        purchases = purchases.map(Purchase::toEntity)
)