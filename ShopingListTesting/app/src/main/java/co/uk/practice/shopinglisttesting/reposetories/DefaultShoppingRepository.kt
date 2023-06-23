package co.uk.practice.shopinglisttesting.reposetories

import androidx.lifecycle.LiveData
import co.uk.practice.shopinglisttesting.data.local.ShoppingDao
import co.uk.practice.shopinglisttesting.data.local.ShoppingItem
import co.uk.practice.shopinglisttesting.data.remote.PixaBayAPI
import co.uk.practice.shopinglisttesting.data.remote.reponses.ImageResponse
import co.uk.practice.shopinglisttesting.other.Resource
import retrofit2.Response
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixaBayAPI: PixaBayAPI
) : ShoppingRepository{
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchImage(imageQuery: String): Resource<ImageResponse> {
        return try{
            val response = pixaBayAPI.searchForImage(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An Unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch(e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}