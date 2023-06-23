package co.uk.practice.shopinglisttesting.data.remote

import co.uk.practice.shopinglisttesting.BuildConfig

import retrofit2.http.GET
import retrofit2.http.Query
import co.uk.practice.shopinglisttesting.data.remote.reponses.ImageResponse
import retrofit2.Response

interface PixaBayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageResponse>

}