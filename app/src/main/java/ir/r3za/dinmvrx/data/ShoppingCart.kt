package ir.r3za.dinmvrx.data

import ir.r3za.dinmvrx.data.model.FoodItem

//TODO it's better to provide with DI in real project
object ShoppingCart {
    private val cartMap: MutableMap<FoodItem, Int> = mutableMapOf()

    fun addToCart(foodItem: FoodItem): Map<FoodItem, Int> {
        val value = if (cartMap.containsKey(foodItem)) cartMap[foodItem] else 0
        cartMap[foodItem] = value!! + 1
        return cartMap.toMap()
    }

    fun removeFromCart(foodItem: FoodItem): Map<FoodItem, Int> {
        cartMap.remove(foodItem)
        return cartMap.toMap()
    }

    fun getCount(): Int = cartMap.map{ it -> it.value }.sum()
}