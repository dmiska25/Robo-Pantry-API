package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantMapper
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import org.springframework.stereotype.Component


@Component
class ProductMapper(private val productVariantMapper: ProductVariantMapper) {
    fun toModel(productEntity: ProductEntity): Product = Product(
        id = productEntity.id,
        name = productEntity.name,
        unitsOnHand = productEntity.unitsOnHand,
        unitOfMeasure = productEntity.unitOfMeasure,
        category = productEntity.category,
        productVariants = productEntity.productVariants.map { productVariantMapper.toModel(it) }
    )

    fun toListingModel(productEntities: List<ProductEntity>): List<Product> {
        return productEntities.map { productEntity ->
            Product(
                id = productEntity.id,
                name = productEntity.name,
                unitsOnHand = productEntity.unitsOnHand,
                unitOfMeasure = productEntity.unitOfMeasure,
                category = productEntity.category,
                productVariants = listOf()
            )
        }
    }

    fun toEntity(product: Product): ProductEntity {
        val productEntity =  ProductEntity(
            id = product.id,
            name = product.name,
            unitsOnHand = product.unitsOnHand,
            unitOfMeasure = product.unitOfMeasure,
            category = product.category,
            productVariants = listOf()
        )
        productEntity.productVariants =
            productVariantMapper.listToEntityList(product.productVariants, productEntity)
        return productEntity
    }
}
