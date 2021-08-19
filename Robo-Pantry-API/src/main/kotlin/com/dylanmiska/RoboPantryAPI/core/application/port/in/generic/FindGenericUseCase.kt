package com.dylanmiska.RoboPantryAPI.core.application.port.`in`.generic

interface FindGenericUseCase<T> {
    fun findAll(): List<T>
    fun find(id: Int): T?
}