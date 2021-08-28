package com.dylanmiska.RoboPantryAPI.core.application.port.`in`.purchase

import com.dylanmiska.RoboPantryAPI.core.domain.model.Expired

interface ManagePurchaseUseCase {
    fun updateExpiration(bond: Expired): Unit
}