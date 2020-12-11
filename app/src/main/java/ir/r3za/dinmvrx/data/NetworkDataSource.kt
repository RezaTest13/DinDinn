package ir.r3za.dinmvrx.data

import io.reactivex.Observable
import ir.r3za.dinmvrx.data.model.FoodCategory
import ir.r3za.dinmvrx.data.model.FoodItem
import ir.r3za.dinmvrx.data.model.TopPagerEntity
import java.math.BigDecimal

class NetworkDataSource {
    // These methods are mock network calls and must change with real api call in a real application
    fun getFoodItems(): Observable<List<FoodItem>> {
        return Observable.fromArray(
            listOf(
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
    }

    fun getTopPagerData(): Observable<TopPagerEntity> {
        return Observable.just(
            TopPagerEntity(
                "Kazarov",
                "delivery",
                listOf(
                    "https://www.foodiesfeed.com/wp-content/uploads/2020/11/cooking-pasta-penne-463x695.jpg",
                    "https://www.foodiesfeed.com/wp-content/uploads/2020/11/small-butter-pumpkin-463x695.jpg",
                    "https://www.foodiesfeed.com/wp-content/uploads/2020/09/noodle-soup-463x648.jpg"
                )
            )
        )
    }

    fun getFoodCategories() : Observable<List<FoodCategory>> {
        return Observable.fromArray(
            listOf(
                FoodCategory("1", "Pizza"),
                FoodCategory("2", "Sushi"),
                FoodCategory("3", "Drinks"),
                FoodCategory("4", "Other"),
            )
        )
    }
}