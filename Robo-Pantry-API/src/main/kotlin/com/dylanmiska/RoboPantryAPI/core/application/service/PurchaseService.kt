package com.dylanmiska.RoboPantryAPI.core.application.service

import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.purchase.ManagePurchaseUseCase
import com.dylanmiska.RoboPantryAPI.core.application.port.out.PurchasePort
import com.dylanmiska.RoboPantryAPI.core.domain.model.Expired
import org.springframework.stereotype.Service
import org.springframework.util.Assert

@Service
class PurchaseService(private val gateway: PurchasePort) : ManagePurchaseUseCase {
    override fun updateExpiration(bond: Expired) {
        // validate purchase id
        val purchase = gateway.find(
            bond.purchaseId
                ?: throw NoSuchElementException("Purchase Id cannot be null!")
        ) ?: throw NoSuchElementException("Purchase with Id ${bond.purchaseId} does not exist!")

        // validate quantity purchased
        Assert.isTrue(
            bond.quantityExpired > 0 && bond.quantityExpired <= purchase.productsPurchased,
            "Quantity Expired must be within valid range!"
        )
        Assert.isTrue(
            bond.expiration.after(purchase.purchaseDate),
            "Expiration date must be after purchase date!"
        )

        // mark purchase as expired
        if (bond.quantityExpired == purchase.productsPurchased) {
            gateway.update(
                purchase.copy(
                    expired = bond
                )
            )

        // change quantity purchased for purchase and persist, then create second purchase record with expiration bond
        } else {
            gateway.update(
                purchase.copy(
                    productsPurchased = purchase.productsPurchased-bond.quantityExpired
                )
            )
            gateway.create(
                purchase.copy(
                    id = null,
                    productsPurchased = bond.quantityExpired,
                    expired = bond
                )
            )
        }
    }

}