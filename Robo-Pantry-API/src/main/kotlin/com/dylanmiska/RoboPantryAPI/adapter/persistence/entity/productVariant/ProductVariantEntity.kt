package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product.ProductEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entityListeners.ProductVarientListeners
import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@EntityListeners(ProductVarientListeners::class)
@Table(name = "product_variant")
data class ProductVariantEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductEntity,
    val brand: String,
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        mappedBy = "productVariant"
    )
    var purchases: List<PurchaseEntity>,
    @Transient
    var productsOnHand: Int?,
    @Column(name = "units_per_product")
    val unitsPerProduct: Double,
    @NaturalId
    val barcode: Int
)
