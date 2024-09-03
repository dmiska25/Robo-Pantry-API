package com.dylanmiska.RoboPantryAPI.adapter.persistence.dao

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseDAO: JpaRepository<PurchaseEntity, Int>
