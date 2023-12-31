package co.uk.practice.shopinglisttesting.di

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.room.Room
import co.uk.practice.shopinglisttesting.data.local.ShoppingDao
import co.uk.practice.shopinglisttesting.data.local.ShoppingItemDatabase
import co.uk.practice.shopinglisttesting.data.remote.PixaBayAPI
import co.uk.practice.shopinglisttesting.other.Constant.BASE_URL
import co.uk.practice.shopinglisttesting.other.Constant.DATABASE_NAME
import co.uk.practice.shopinglisttesting.reposetories.DefaultShoppingRepository
import co.uk.practice.shopinglisttesting.reposetories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providesDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixaBayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository

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