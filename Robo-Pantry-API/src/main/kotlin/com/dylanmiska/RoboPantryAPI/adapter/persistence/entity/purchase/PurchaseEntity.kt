package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.expired.ExpiredEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import org.hibernate.annotations.Where
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "purchase")
@Where(clause = "expired_id IS NULL")
data class PurchaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductEntity,
    @ManyToOne
    @JoinColumn(name = "product_variant_id", nullable = false)
    val productVariant: ProductVariantEntity,
    @Column(name = "purchase_date")
    val purchaseDate: Date,
    @Column(name = "products_purchased")
    val productsPurchased: Int,
    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "expired_id")
    val expired: ExpiredEntity?
)