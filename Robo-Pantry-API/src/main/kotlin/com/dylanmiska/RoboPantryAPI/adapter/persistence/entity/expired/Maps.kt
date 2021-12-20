package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired

import com.dylanmiska.RoboPantryAPI.core.domain.model.Expired

fun ExpiredEntity.toModel(itemsPurchased: Int): Expired = Expired(
    id = id,
    quantityExpired = itemsPurchased,
    reason = reason,
    expiration = expiration
)

fun Expired.toEntity(): ExpiredEntity = ExpiredEntity(
    id = id,
    reason = reason,
    expiration = expiration
)