package com.dylanmiska.RoboPantryAPI.common.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum class ProductCategory {
    @JsonProperty("beverage")
    BEVERAGE,
    @JsonProperty("bakery")
    BAKERY,
    @JsonProperty("canned_good")
    CANNED_GOOD,
    @JsonProperty("dairy")
    DAIRY,
    @JsonProperty("dry_good")
    DRY_GOOD,
    @JsonProperty("frozen")
    FROZEN,
    @JsonProperty("meat")
    MEAT,
    @JsonProperty("produce")
    PRODUCE,
    @JsonProperty("cleaner")
    CLEANER,
    @JsonProperty("paper_good")
    PAPER_GOOD,
    @JsonProperty("personal_care")
    PERSONAL_CARE,
    @JsonProperty("other")
    OTHER
}