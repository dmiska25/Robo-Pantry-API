package com.dylanmiska.RoboPantryAPI.adapter.web.controller

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.expired.ExpirationRequest
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.expired.toModel
import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.purchase.ManagePurchaseUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/robo-pantry"])
class PurchaseController(
    private val manage: ManagePurchaseUseCase
) {
    @PutMapping("/purchase/expiration")
    fun updateExpiration(@RequestBody expirationRequest: ExpirationRequest): ResponseEntity<String> {
        manage.updateExpiration(expirationRequest.toModel())
        return ResponseEntity(HttpStatus.OK)
    }
}