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
    productVariants = productVariants.map(ProductVariantEntity::toModel)
)

fun ProductEntity.toListingModel(): Product = Product(
    id = id,
    name = name,
    unitsOnHand = unitsOnHand,
    unitOfMeasure = unitOfMeasure,
    category = category,
    productVariants = listOf()
)

fun Product.toEntity(): ProductEntity  = ProductEntity(
        id = id,
        name = name,
        unitsOnHand = unitsOnHand,
        unitOfMeasure = unitOfMeasure,
        category = category,
        productVariants = productVariants.map(ProductVariant::toEntity).toMutableList()
)
