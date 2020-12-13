package ir.r3za.dinmvrx.data

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.r3za.dinmvrx.data.model.CartItem
import ir.r3za.dinmvrx.data.model.FoodCategory
import ir.r3za.dinmvrx.data.model.FoodItem
import ir.r3za.dinmvrx.data.model.TopPagerEntity
import java.util.concurrent.TimeUnit

//TODO it's better to provide with DI in real project
object Repository {

    private val networkDataSource = NetworkDataSource()

    fun getFoodItems(): Observable<List<FoodItem>> {
        return networkDataSource.getFoodItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(1500, TimeUnit.MILLISECONDS)
    }

    fun getTopPagerData(): Observable<TopPagerEntity> {
        return networkDataSource.getTopPagerData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(2000, TimeUnit.MILLISECONDS)
    }

    fun getFoodCategories(): Observable<List<FoodCategory>> {
        return networkDataSource.getFoodCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(800, TimeUnit.MILLISECONDS)
    }

    fun getShoppingCartCount(): Observable<Int> {
        return Observable.fromCallable {
            ShoppingCart.getCount()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun removeFromCart(foodItem: FoodItem): Observable<List<CartItem>> {
        return Observable.fromCallable {
            ShoppingCart.removeFromCart(foodItem)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}