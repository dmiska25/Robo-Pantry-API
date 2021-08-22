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
    brand = brand,
    productsOnHand = productsOnHand,
    unitsPerProduct = unitsPerProduct,
    barcode = barcode,
    purchases = purchasesToModel()
)

fun ProductVariantEntity.purchasesToModel(): List<Purchase> =
    purchases.map(PurchaseEntity::toModel)

fun ProductVariant.toEntity(product: ProductEntity): ProductVariantEntity {
    val productVariant = ProductVariantEntity(
        id = id,
        brand = brand,
        product = product,
        productsOnHand = productsOnHand,
        unitsPerProduct = unitsPerProduct,
        barcode = barcode,
        purchases = mutableListOf()
    )
    productVariant.purchases.addAll(purchasesToEntities(product, productVariant))
    return productVariant
}

fun ProductVariant.purchasesToEntities(product: ProductEntity,productVariant: ProductVariantEntity): List<PurchaseEntity> =
    purchases.map{ it.toEntity(product, productVariant) }

