package com.dylanmiska.RoboPantryAPI.adapter.persistence.dao

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDAO: JpaRepository<ProductEntity, Int>