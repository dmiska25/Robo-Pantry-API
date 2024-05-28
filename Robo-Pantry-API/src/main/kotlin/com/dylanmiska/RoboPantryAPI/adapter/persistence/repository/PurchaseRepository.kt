package com.dylanmiska.RoboPantryAPI.adapter.persistence.repository

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.PurchaseDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseMapper
import com.dylanmiska.RoboPantryAPI.core.application.port.out.PurchasePort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import org.springframework.stereotype.Repository

@Repository
open class PurchaseRepository(
    private val dao: PurchaseDAO,
    private val mapper: PurchaseMapper
): PurchasePort {
    override fun find(id: Int): Purchase? {
        return mapper.toModel(dao.getById(id))
    }

    override fun findAll(): List<Purchase> {
        return dao.findAll().map { mapper.toModel(it) }
    }

    override fun create(element: Purchase) {
        dao.save(mapper.toEntity(element))
    }

    override fun update(element: Purchase) {
        val purchase = mapper.toEntity(element)
        dao.save(purchase)
    }

    override fun delete(id: Int) {
        dao.deleteById(id)
    }
}