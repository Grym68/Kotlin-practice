package co.uk.practice.retrofitexample.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    /**
     * https://rickandmortyapi.com/api/character/?page=1
     * The retrofit builder will need a base url so we
     * extract that from our link and create the base url variable
     * of type String
     */
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    /**
     * Next we create a variable for the moshi builder,
     * adding a converter to it
     */
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    /**
     * Then we create an instance of Retfoti by lazy
     * so it can be initialized only when it is needed
     * pass the base url and the moshi variavble create
     * above to the Builder
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

/**
 * This interface defines how the Retrofit talks
 * to the service using the Get Method
 */
interface ApiService{
    /**
     * this method will get the characters from
     * the page specified as the function parameter
     * the annotation of GET with the prompt of "character"
     * is used to fetch the data of "character" in our api call
     *
     * returns -
     */
    @GET("character")
    fun fetchCharacters(
        @Query("page")
        page: String
    ): Call<CharacterResponse>
}