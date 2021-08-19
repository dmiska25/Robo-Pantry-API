package com.dylanmiska.RoboPantryAPI.adapter.web.contract.response.purchase

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class PurchaseResponse (
    val id: Int,
    @JsonProperty("purchase_date")
    val purchaseDate: Date,
    @JsonProperty("products_purchased")
    val productsPurchased: Int
)