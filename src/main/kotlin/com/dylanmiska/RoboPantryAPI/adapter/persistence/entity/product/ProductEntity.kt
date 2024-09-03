package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.purchase.PurchaseEntity
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import jakarta.persistence.*
import org.hibernate.annotations.NaturalId

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq_gen")
    @SequenceGenerator(name = "hibernate_seq_gen", sequenceName = "hibernate_sequence", allocationSize = 1)
    val id: Int?,
    val name: String,
    @Enumerated(EnumType.STRING)
    val category: ProductCategory,
    @Enumerated(EnumType.STRING)
    @Column(name = "unit_of_measure")
    val unitOfMeasure: UnitOfMeasure,
    val brand: String,
    @OneToMany(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        mappedBy = "productId"
    )
    var purchases: List<PurchaseEntity>,
    @Column(name = "units_per_product")
    val unitsPerProduct: Double,
    @NaturalId
    val barcode: Int
) {
    fun getProductsOnHand() = purchases.sumOf { it.productsPurchased }
}
