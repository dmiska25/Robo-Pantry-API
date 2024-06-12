package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired

import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "expired")
data class ExpiredEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    @Enumerated(EnumType.STRING)
    val reason: ExpirationReason,
    val expiration: Date
) {
    constructor() : this(-1, ExpirationReason.EXPIRED, Date())
}
