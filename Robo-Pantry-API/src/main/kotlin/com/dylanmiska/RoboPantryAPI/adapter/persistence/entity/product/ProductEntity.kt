package com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.product

import com.dylanmiska.RoboPantryAPI.adapter.persistence.entity.productVariant.ProductVariantEntity
import com.dylanmiska.RoboPantryAPI.adapter.persistence.entityListeners.ProductListeners
import com.dylanmiska.RoboPantryAPI.common.enums.ProductCategory
import com.dylanmiska.RoboPantryAPI.common.enums.UnitOfMeasure
import org.hibernate.annotations.NaturalId
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "product")
@EntityListeners(ProductListeners::class)
data class ProductEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,
        @NaturalId
        val name: String,
        @Enumerated(EnumType.STRING)
        val category: ProductCategory,
        @Enumerated(EnumType.STRING)
        @Column(name = "unit_of_measure")
        val unitOfMeasure: UnitOfMeasure,
        @OneToMany(
                fetch = FetchType.LAZY,
                cascade = [CascadeType.ALL],
                mappedBy = "product")
        val productVariants: MutableList<ProductVariantEntity>,
        @Transient
        var unitsOnHand: Double?
)


