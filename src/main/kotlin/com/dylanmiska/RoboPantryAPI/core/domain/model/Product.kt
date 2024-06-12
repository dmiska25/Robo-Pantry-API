package com.dylanmiska.RoboPantryAPI.core.domain.model

import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure

data class Product(
    val id: Int? = null,
    val name: String,
    val category: ProductCategory,
    val unitOfMeasure: UnitOfMeasure,
    val productVariants: List<ProductVariant>,
    val unitsOnHand: Double? = null
)
