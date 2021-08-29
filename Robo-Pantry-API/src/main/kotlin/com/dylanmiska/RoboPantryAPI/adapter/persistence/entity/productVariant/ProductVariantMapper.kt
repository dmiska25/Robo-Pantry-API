package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.ProductDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseMapper
import com.dylanmiska.RoboPantryAPI.core.domain.model.ProductVariant
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductVariantMapper(private val productDAO: ProductDAO, private val purchaseMapper: PurchaseMapper) {

    fun toEntity(productVariant: ProductVariant, productEntity: ProductEntity): ProductVariantEntity {
        val productVariantEntity = ProductVariantEntity(
            id = productVariant.id,
            brand = productVariant.brand,
            product = productEntity,
            productsOnHand = productVariant.productsOnHand,
            unitsPerProduct = productVariant.unitsPerProduct,
            barcode = productVariant.barcode,
            purchases = listOf()
        )
        return productVariantEntity.copy(
            purchases = purchaseMapper.listToEntityList(productVariant.purchases, productVariantEntity, productEntity)
        )
    }


    fun listToEntityList(productVariants: List<ProductVariant>, productEntity: ProductEntity): List<ProductVariantEntity> {
        return productVariants.map {
            val productVariantEntity = ProductVariantEntity(
                it.id,
                productEntity,
                it.brand,
                listOf(),
                it.productsOnHand,
                it.unitsPerProduct,
                it.barcode
            )
            productVariantEntity.purchases =
                purchaseMapper.listToEntityList(it.purchases, productVariantEntity, productEntity)
            return@map productVariantEntity
        }
    }

    fun toModel(productVariantEntity: ProductVariantEntity): ProductVariant = ProductVariant(
        id = productVariantEntity.id,
        productId = productVariantEntity.product.id!!,
        brand = productVariantEntity.brand,
        productsOnHand = productVariantEntity.productsOnHand,
        unitsPerProduct = productVariantEntity.unitsPerProduct,
        barcode = productVariantEntity.barcode,
        purchases = productVariantEntity.purchases.map{ purchaseMapper.toModel(it) }
    )
}