package ir.r3za.dinmvrx

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import ir.r3za.dinmvrx.base.MvRxViewModel
import ir.r3za.dinmvrx.data.FoodCategory
import ir.r3za.dinmvrx.data.FoodItem

data class MainState(
    val categories: Async<List<FoodCategory>> = Uninitialized,
    val foodList: Async<List<FoodItem>> = Uninitialized
) : MvRxState

class MainViewModel(val initialState: MainState) : MvRxViewModel<MainState>(initialState) {

}