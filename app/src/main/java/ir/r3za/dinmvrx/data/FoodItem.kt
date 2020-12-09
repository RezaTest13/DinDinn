package ir.r3za.dinmvrx.data

import java.math.BigDecimal

data class FoodItem(
    val name: String,
    val description: String?,
    val specifications: String?,
    val price: BigDecimal
)