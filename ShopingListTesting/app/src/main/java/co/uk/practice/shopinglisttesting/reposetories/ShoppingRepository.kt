package co.uk.practice.shopinglisttesting.reposetories

import androidx.lifecycle.LiveData
import co.uk.practice.shopinglisttesting.data.local.ShoppingItem
import co.uk.practice.shopinglisttesting.data.remote.reponses.ImageResponse
import co.uk.practice.shopinglisttesting.other.Resource
import retrofit2.Response

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchImage(imageQuery: String): Resource<ImageResponse>
}