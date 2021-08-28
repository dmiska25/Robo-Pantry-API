package com.dylanmiska.RoboPantryAPI.core.application.service

import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.purchase.ManagePurchaseUseCase
import com.dylanmiska.RoboPantryAPI.core.application.port.out.PurchasePort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Expired
import org.springframework.stereotype.Service

@Service
class PurchaseService(gateway: PurchasePort): ManagePurchaseUseCase {
    override fun updateExpiration(bond: Expired) {
        TODO()
        // does expiration bond have quantity expired? no-> throw exception
        // does purchase exist? -> is quantity expired less then purchase quantity? -> if equal add experation with given
        // info, else split purchase into two add expiration to one and update info for other.
    }

}