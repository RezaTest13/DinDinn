package ir.r3za.dinmvrx

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
    val topLoading: Boolean = true,
    val categories: Async<List<FoodCategory>> = Uninitialized,
    val foodList: Async<List<FoodItem>> = Uninitialized,
    val topPagerEntity: Async<TopPagerEntity> = Uninitialized,
) : MvRxState

class MainViewModel(private val initialState: MainState) : MvRxViewModel<MainState>(initialState) {

    init {
        fetchData()
    }

    private fun fetchData() {
        Repository.getTopPagerData()
            .doOnSubscribe {
                setState { copy(topLoading = true) }
            }
            .doOnComplete {
                setState { copy(topLoading = false) }
            }
            .execute {
                copy(topPagerEntity = it)
            }

        Repository.getFoodItems().execute {
            copy(foodList = it)
        }

    }

    fun addToCart(foodItem: FoodItem) {
        ShoppingCart.addToCart(foodItem)
    }

}