package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseMapper
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import org.springframework.stereotype.Component

@Component
class ProductMapper(private val purchaseMapper: PurchaseMapper) {

    fun toEntity(product: Product): ProductEntity {
        val productEntity = ProductEntity(
            id = product.id,
            name = product.name,
            category = product.category,
            unitOfMeasure = product.unitOfMeasure,
            brand = product.brand,
            unitsPerProduct = product.unitsPerProduct,
            barcode = product.barcode,
            purchases = listOf()
        )
        productEntity.purchases =
            purchaseMapper.listToEntityList(product.purchases, productEntity)
        return productEntity
    }

    fun listToEntityList(products: List<Product>): List<ProductEntity> {
        return products.map {
            val productEntity = ProductEntity(
                it.id,
                it.name,
                it.category,
                it.unitOfMeasure,
                it.brand,
                listOf(),
                it.unitsPerProduct,
                it.barcode
            )
            productEntity.purchases =
                purchaseMapper.listToEntityList(it.purchases, productEntity)
            return@map productEntity
        }
    }

    fun toModel(productEntity: ProductEntity): Product = Product(
        id = productEntity.id,
        name = productEntity.name,
        category = productEntity.category,
        unitOfMeasure = productEntity.unitOfMeasure,
        brand = productEntity.brand,
        productsOnHand = productEntity.getProductsOnHand(),
        unitsPerProduct = productEntity.unitsPerProduct,
        barcode = productEntity.barcode,
        purchases = purchaseMapper.listToModelList(productEntity.purchases)
    )

    fun toListingModel(productEntities: List<ProductEntity>): List<Product> =
        productEntities.map(::toModel)
}
