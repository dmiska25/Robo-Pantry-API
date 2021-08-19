package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.productVariant.toModel
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase.toModel
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant

fun ProductRequest.toModel(productVariant: ProductVariant): Product = Product(
    id = id,
    name = name,
    category = category,
    unitOfMeasure = unitOfMeasure,
    productVariants = listOf(productVariant)
)

fun EmbeddedProductRequest.toModel(): Product =
    product.toModel(
        productVariant.toModel(
            purchase.toModel()
        )
    )