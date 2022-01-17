package com.dylanmiska.RoboPantryAPI.adapter.web.controller

import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product.EmbeddedProductRequest
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.product.toModel
import com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.product.*
import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.FindProductUseCase
import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product.ManageProductUseCase
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(path = ["/robo-pantry"])
class ProductController(
        private val findProductUseCase: FindProductUseCase,
        private val manageProductUseCase: ManageProductUseCase
) {
    @GetMapping("/products")
    fun getProductListing(): ResponseEntity<ProductListWrapperResponse> {
        return ResponseEntity.ok(
            ProductListWrapperResponse(findProductUseCase.findAll().map(Product::toListResponse))
        )
    }

    @GetMapping("/products/{id}")
    fun getProduct(@PathVariable id: Int): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(
            findProductUseCase.find(id)?.toResponse() ?:
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        )
    }

    @PostMapping("/products")
    fun createProduct(
        @RequestBody
        EmbeddedProductRequest: EmbeddedProductRequest
    ): ResponseEntity<String> {
        manageProductUseCase.create(EmbeddedProductRequest.toModel())
        return ResponseEntity<String>(HttpStatus.CREATED)
    }
}