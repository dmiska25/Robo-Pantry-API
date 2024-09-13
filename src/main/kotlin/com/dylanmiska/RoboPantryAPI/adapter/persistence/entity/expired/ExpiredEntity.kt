package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired

import com.dylanmiska.RoboPantryAPI.common.enums.ExpirationReason
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "expired")
data class ExpiredEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq_gen")
    @SequenceGenerator(name = "hibernate_seq_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
    val id: Int?,
    @Enumerated(EnumType.STRING)
    val reason: ExpirationReason,
    val expiration: Date
)
