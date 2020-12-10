package ir.r3za.dinmvrx

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import ir.r3za.dinmvrx.base.MvRxViewModel
import ir.r3za.dinmvrx.data.ShoppingCart
import ir.r3za.dinmvrx.data.model.FoodCategory
import ir.r3za.dinmvrx.data.model.FoodItem
import ir.r3za.dinmvrx.data.model.TopPagerEntity
import java.math.BigDecimal

data class MainState(
    val categories: Async<List<FoodCategory>> = Uninitialized,
    val foodList: Async<List<FoodItem>> = Uninitialized,
    val topPagerEntity: Async<TopPagerEntity> = Uninitialized,
) : MvRxState

class MainViewModel(private val initialState: MainState) : MvRxViewModel<MainState>(initialState) {

    init {
        fetchData()
    }

    private fun fetchData() {
        setState {
            initialState.copy(
                topPagerEntity = Success(
                    TopPagerEntity(
                        "Kazarov",
                        "delivery",
                        listOf(
                            "https://www.foodiesfeed.com/wp-content/uploads/2020/11/cooking-pasta-penne-463x695.jpg",
                            "https://www.foodiesfeed.com/wp-content/uploads/2020/11/small-butter-pumpkin-463x695.jpg",
                            "https://www.foodiesfeed.com/wp-content/uploads/2020/09/noodle-soup-463x648.jpg"
                        )
                    )
                ),
                foodList = Success<List<FoodItem>>(
                    listOf<FoodItem>(
                        FoodItem(
                            "Pizza",
                            "pizza is delicious",
                            "120 gram",
                            "https://www.foodiesfeed.com/wp-content/uploads/2019/02/pizza-ready-for-baking-463x309.jpg",
                            BigDecimal(24.2)
                        ), FoodItem(
                            "Pizza",
                            "pizza is delicious, pizza is delicious, pizza is delicious, pizza is delicious, pizza is delicious",
                            "120 gram",
                            "https://www.foodiesfeed.com/wp-content/uploads/2019/05/colorful-healthy-fresh-berries-in-a-cup-1-463x695.jpg",
                            BigDecimal(24.2)
                        ), FoodItem(
                            "Pizza",
                            "pizza is delicious",
                            "120 gram",
                            "https://www.foodiesfeed.com/wp-content/uploads/2019/07/neapolitan-pizza-margherita-463x309.jpg",
                            BigDecimal(24.2)
                        ),
                    )
                )
            )
        }
    }

    fun addToCart(foodItem: FoodItem) {
        ShoppingCart.addToCart(foodItem)
    }

}