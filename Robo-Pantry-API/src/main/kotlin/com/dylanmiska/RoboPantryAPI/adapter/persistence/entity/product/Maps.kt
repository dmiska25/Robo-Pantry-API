package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.toModel
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant

fun ProductEntity.toModel(): Product = Product(
    id = id,
    name = name,
    unitsOnHand = unitsOnHand,
    unitOfMeasure = unitOfMeasure,
    category = category,
    productVariants = productVariantsToModel()
)

fun ProductEntity.productVariantsToModel(): List<ProductVariant> =
    productVariants.map(ProductVariantEntity::toModel)

fun ProductEntity.toListingModel(): Product = Product(
    id = id,
    name = name,
    unitsOnHand = unitsOnHand,
    unitOfMeasure = unitOfMeasure,
    category = category,
    productVariants = listOf()
)

fun Product.toEntity(): ProductEntity {
    val product = ProductEntity(
        id = id,
        name = name,
        unitsOnHand = unitsOnHand,
        unitOfMeasure = unitOfMeasure,
        category = category,
        productVariants = mutableListOf()
    )
    product.productVariants.addAll(
        productVariantsToEntites(product)
    )
    return product
}

fun Product.productVariantsToEntites(product: ProductEntity): List<ProductVariantEntity> =
    productVariants.map { it.toEntity(product) }
