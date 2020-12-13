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
                    1,
                    "Pizza 1",
                    "pizza is delicious",
                    "120 gram",
                    "https://www.foodiesfeed.com/wp-content/uploads/2019/02/pizza-ready-for-baking-463x309.jpg",
                    BigDecimal(25),
                    "pizza"
                ),
                FoodItem(
                    2,
                    "Pizza 2",
                    "pizza is delicious, pizza is delicious, pizza is delicious, pizza is delicious, pizza is delicious",
                    "120 gram",
                    "https://www.foodiesfeed.com/wp-content/uploads/2019/05/colorful-healthy-fresh-berries-in-a-cup-1-463x695.jpg",
                    BigDecimal(40),
                    "pizza"
                ),
                FoodItem(
                    3,
                    "Pizza 3",
                    "pizza is delicious",
                    "120 gram",
                    "https://www.foodiesfeed.com/wp-content/uploads/2019/07/neapolitan-pizza-margherita-463x309.jpg",
                    BigDecimal(51),
                    "pizza"
                ), FoodItem(
                    4,
                    "Sushi 1",
                    "Sushi is delicious",
                    "120 gram",
                    "https://cdn.cheapoguides.com/wp-content/uploads/sites/2/2017/08/9993631716_2b532586b3_b-1-770x514.jpg",
                    BigDecimal(51),
                    "sushi"
                ), FoodItem(
                    5,
                    "Sushi 2",
                    "pizza is delicious",
                    "120 gram",
                    "https://cdn.cheapoguides.com/wp-content/uploads/sites/2/2018/08/sashimi-2563650_1280-770x578.jpg",
                    BigDecimal(51),
                    "sushi"
                )
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
                    "https://image.freepik.com/free-photo/sushi-roll-japanese-food-restaurant-vertical-photo_72474-462.jpg",
                    "https://www.foodiesfeed.com/wp-content/uploads/2020/09/noodle-soup-463x648.jpg"
                )
            )
        )
    }

    fun getFoodCategories(): Observable<List<FoodCategory>> {
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