package com.dylanmiska.RoboPantryAPI.adapter.persistence.dao

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PurchaseDAO: JpaRepository<PurchaseEntity, Int> {
    @Query("SELECT SUM(u.productsPurchased) FROM PurchaseEntity u " +
                 "WHERE u.productVariant = ?1")
    fun sumProductsByProductVariant(productVariant: ProductVariantEntity): Int

    @Query("SELECT SUM(pv.unitsPerProduct * p.productsPurchased) " +
            "FROM PurchaseEntity p " +
            "JOIN p.productVariant pv " +
            "WHERE p.product = ?1 ")
    fun sumUnitsByProduct(product: ProductEntity): Double
}