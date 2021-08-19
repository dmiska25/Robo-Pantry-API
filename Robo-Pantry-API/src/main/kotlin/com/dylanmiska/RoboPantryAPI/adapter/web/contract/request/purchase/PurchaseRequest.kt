package com.dylanmiska.RoboPantryAPI.adapter.web.contract.request.purchase

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class PurchaseRequest (
    @JsonProperty("purchase_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val purchaseDate: Date,
    @JsonProperty("products_purchased")
    val productsPurchased: Int
)