package ir.r3za.dinmvrx.data

import ir.r3za.dinmvrx.data.model.CartItem
import ir.r3za.dinmvrx.data.model.FoodItem
import java.math.BigDecimal

//TODO it's better to provide with DI in real project
object ShoppingCart {
    private val _cart: MutableList<CartItem> = mutableListOf()
    val cart: List<CartItem> get() = _cart
    fun addToCart(foodItem: FoodItem): List<CartItem> {
        _cart.find { it ->
            it.foodItem.id == foodItem.id
        }?.let {
            it.count++
        } ?: _cart.add(CartItem(foodItem, 1))
        return _cart
    }

    fun removeFromCart(foodItem: FoodItem): List<CartItem> {
        for (i in 0 until _cart.size) {
            if (_cart[i].foodItem.id == foodItem.id) {
                _cart.removeAt(i)
                break
            }
        }
        return cart
    }

    fun getCount(): Int = _cart.map { it.count }.sum()

    fun getTotalPrice(): BigDecimal =
        _cart.map { it.foodItem.price.multiply(it.count.toBigDecimal()) }.takeIf { it.isNotEmpty() }
            ?.reduce { acc, bigDecimal -> acc + bigDecimal } ?: BigDecimal.ZERO
}