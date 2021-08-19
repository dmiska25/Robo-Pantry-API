package com.dylanmiska.RoboPantryAPI.adapter.persistence.dao

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductVariantDAO: JpaRepository<ProductVariantEntity, Int>