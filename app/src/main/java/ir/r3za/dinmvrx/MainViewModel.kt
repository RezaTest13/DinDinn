package ir.r3za.dinmvrx

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import ir.r3za.dinmvrx.base.MvRxViewModel
import ir.r3za.dinmvrx.data.FoodCategory
import ir.r3za.dinmvrx.data.FoodItem
import java.math.BigDecimal

data class MainState(
    val categories: Async<List<FoodCategory>> = Uninitialized,
    val foodList: Async<List<FoodItem>> = Uninitialized
) : MvRxState

class MainViewModel(val initialState: MainState) : MvRxViewModel<MainState>(initialState) {

    init {
        fetchData()
    }

    private fun fetchData() {
        setState {
            initialState.copy(
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

}