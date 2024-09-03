package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.ExpiredEntity
import jakarta.persistence.*
import org.hibernate.annotations.Where
import java.util.*

@Entity
@Table(name = "purchase")
@Where(clause = "expired_id IS NULL")
data class PurchaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq_gen")
    @SequenceGenerator(name = "hibernate_seq_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
    val id: Int?,
    @Column(name = "product_id", nullable = false)
    val productId: Int,
    @Column(name = "purchase_date")
    val purchaseDate: Date,
    @Column(name = "products_purchased")
    val productsPurchased: Int,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "expired_id")
    val expired: ExpiredEntity?
)