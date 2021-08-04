package com.dylanmiska.RoboPantryAPI.core.domain.model

import java.util.*

data class Product(
        val id: Int? = null,
        val name: String,
        val purchaseDate: Date,
        val quantity: Double,
        val unitOfMeasure: UnitOfMeasure,
        val barcode: Long
)
