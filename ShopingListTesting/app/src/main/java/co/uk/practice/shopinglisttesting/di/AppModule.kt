package co.uk.practice.shopinglisttesting.di

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.room.Room
import co.uk.practice.shopinglisttesting.data.local.ShoppingDao
import co.uk.practice.shopinglisttesting.data.local.ShoppingItemDatabase
import co.uk.practice.shopinglisttesting.data.remote.PixaBayAPI
import co.uk.practice.shopinglisttesting.other.Constant.BASE_URL
import co.uk.practice.shopinglisttesting.other.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponentManager::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixaBayAPI() : PixaBayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixaBayAPI::class.java)
    }
}