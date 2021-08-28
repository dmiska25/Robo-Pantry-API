package com.dylanmiska.RoboPantryAPI.adapter.persistence.repository

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.PurchaseDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.toModel
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.toEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.toModel
import com.dylanmiska.RoboPantryAPI.core.application.port.out.PurchasePort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import org.springframework.stereotype.Repository

@Repository
class PurchaseRepository(private val dao: PurchaseDAO): PurchasePort {
    override fun find(id: Int): Purchase? {
        return dao.getById(id).toModel()
    }

    override fun findAll(): List<Purchase> {
        return dao.findAll().map(PurchaseEntity::toModel)
    }

    override fun create(element: Purchase) {
        dao.save(element.toEntity())
    }

    override fun update(element: Purchase) {
        dao.save(element.toEntity())
    }

    override fun delete(id: Int) {
        dao.deleteById(id)
    }
}