package com.dylanmiska.RoboPantryAPI.core.application.port.`in`.generic

interface ManageGenericUseCase<T> {
    fun create(element: T)
    fun update(element: T)
    fun delete(id: Int)
}