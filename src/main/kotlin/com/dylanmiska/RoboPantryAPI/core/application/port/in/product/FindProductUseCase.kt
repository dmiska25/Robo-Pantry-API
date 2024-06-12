package com.dylanmiska.RoboPantryAPI.core.application.port.`in`.product

import com.dylanmiska.RoboPantryAPI.core.application.port.`in`.generic.FindGenericUseCase
import com.dylanmiska.RoboPantryAPI.core.domain.model.Product

interface FindProductUseCase: FindGenericUseCase<Product>