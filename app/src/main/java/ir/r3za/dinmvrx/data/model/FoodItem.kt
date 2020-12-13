package ir.r3za.dinmvrx.data.model

import java.math.BigDecimal

data class FoodItem(
    val id: Int,
    val name: String,
    val description: String?,
    val specifications: String?,
    val imageUrl: String,
    val price: BigDecimal,
    val category: String,
    @Transient var adding: Boolean = false
)