package com.dylanmiska.RoboPantryAPI.adapter.persistence.repository

import com.dylanmiska.RoboPantryAPI.adapter.persistence.dao.PurchaseDAO
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseMapper
import com.dylanmiska.RoboPantryAPI.core.application.port.out.PurchasePort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Purchase
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PurchaseRepository(
    private val dao: PurchaseDAO,
    private val mapper: PurchaseMapper
): PurchasePort {
    override fun find(id: Int): Purchase? {
        return dao.findByIdOrNull(id)?.let { mapper.toModel(it) }
    }

    override fun findAll(): List<Purchase> {
        return dao.findAll().map { mapper.toModel(it) }
    }

    override fun create(element: Purchase): Purchase {
        return mapper.toModel(dao.save(mapper.toEntity(element)))
    }

    override fun update(element: Purchase): Purchase {
        return mapper.toModel(dao.save(mapper.toEntity(element)))
    }

    override fun delete(id: Int) {
        dao.deleteById(id)
    }
}