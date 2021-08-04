package com.dylanmiska.RoboPantryAPI.core.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

enum class UnitOfMeasure() {
    @JsonProperty("tsp")
    TEASPOON,
    @JsonProperty("tbsp")
    TABLESPOON,
    @JsonProperty("oz")
    OUNCE,
    @JsonProperty("cup")
    CUP,
    @JsonProperty("pt")
    PINT,
    @JsonProperty("qt")
    QUART,
    @JsonProperty("gal")
    GALLON,
    @JsonProperty("lb")
    POUND,
    @JsonProperty("g")
    GRAM,
    @JsonProperty("kg")
    KILOGRAM,
    @JsonProperty("l")
    LITER,
    @JsonProperty("ml")
    MILLILITER,
    @JsonProperty("unit")
    UNIT

}