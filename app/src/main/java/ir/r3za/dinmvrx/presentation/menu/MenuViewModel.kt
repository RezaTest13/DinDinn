package ir.r3za.dinmvrx.presentation.menu

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import ir.r3za.dinmvrx.base.MvRxViewModel
import ir.r3za.dinmvrx.data.Repository
import ir.r3za.dinmvrx.data.ShoppingCart
import ir.r3za.dinmvrx.data.model.FoodCategory
import ir.r3za.dinmvrx.data.model.FoodItem
import ir.r3za.dinmvrx.data.model.TopPagerEntity

data class MainState(
    val topLoading: Boolean = false,
    val categories: Async<List<FoodCategory>> = Uninitialized,
    val foodList: Async<List<FoodItem>> = Uninitialized,
    val shoppingCartCount: Async<Int> = Uninitialized,
    val topPagerEntity: Async<TopPagerEntity> = Uninitialized
) : MvRxState

class MainViewModel(private val initialState: MainState) : MvRxViewModel<MainState>(initialState) {

    init {
        fetchData()
    }

    private fun fetchData() {
        Repository.getTopPagerData()
            .doOnSubscribe {
                setState { initialState.copy(topLoading = true) }
            }
            .doOnError {
                setState { initialState.copy(topLoading = false) }
            }
            .execute {
                initialState.copy(topPagerEntity = it, topLoading = false)
            }

        Repository.getFoodItems().execute {
            initialState.copy(foodList = it)
        }

        Repository.getFoodCategories().execute {
            initialState.copy(categories = it)
        }

    }

    fun addToCart(foodItem: FoodItem) {
        ShoppingCart.addToCart(foodItem)
        Repository.getShoppingCartCount().execute {
            initialState.copy(shoppingCartCount = it)
        }
    }

}