package ir.r3za.dinmvrx.presentation.cart

import com.airbnb.mvrx.MvRxState
import ir.r3za.dinmvrx.base.MvRxViewModel
import ir.r3za.dinmvrx.data.ShoppingCart
import ir.r3za.dinmvrx.data.model.CartItem
import ir.r3za.dinmvrx.data.model.FoodItem
import java.math.BigDecimal

data class CartState(
    val cart: List<CartItem> = listOf(),
    val totalPrice: BigDecimal = BigDecimal.ZERO
) : MvRxState

class CartViewModel(initialState: CartState) : MvRxViewModel<CartState>(initialState) {

    init {
        setState {
            copy(
                cart = ShoppingCart.cart.toList(),
                totalPrice = ShoppingCart.getTotalPrice()
            )
        }
    }

    fun deleteFoodItem(foodItem: FoodItem) = withState { state ->
        setState {
            copy(
                cart = ShoppingCart.removeFromCart(foodItem).toMutableList(),
                totalPrice = ShoppingCart.getTotalPrice()
            )
        }
    }
}
