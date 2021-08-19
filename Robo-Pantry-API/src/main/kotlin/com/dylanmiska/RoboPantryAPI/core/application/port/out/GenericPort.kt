package com.dylanmiska.RoboPantryAPI.core.application.port.out

interface GenericPort<T> {
    fun find(id: Int): T?
    fun findAll(): List<T>
    fun create(element: T)
    fun update(element: T)
    fun delete(id: Int)
}