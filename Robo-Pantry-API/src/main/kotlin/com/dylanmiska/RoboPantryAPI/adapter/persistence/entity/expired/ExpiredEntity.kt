package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired

import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "expired")
data class ExpiredEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    @Enumerated(EnumType.STRING)
    val reason: ExpirationReason,
    val expiration: Date
)
