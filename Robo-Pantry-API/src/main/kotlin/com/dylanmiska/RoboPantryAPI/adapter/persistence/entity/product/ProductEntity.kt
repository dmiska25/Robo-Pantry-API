package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product

import com.dylanmiska.RoboPantryAPI.core.domain.model.UnitOfMeasure
import java.util.*
import javax.persistence.*

@Entity
data class ProductEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        val name: String,
        @Column(name = "purchase_date")
        val purchaseDate: Date,
        val quantity: Double,
        @Enumerated(EnumType.STRING)
        @Column(name = "unit_of_measure")
        val unitOfMeasure: UnitOfMeasure,
        val barcode: Long
)
