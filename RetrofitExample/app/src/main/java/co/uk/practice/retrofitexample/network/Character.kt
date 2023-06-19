package co.uk.practice.retrofitexample.network

import com.squareup.moshi.Json
import retrofit2.http.Url

data class Character(
    @Json(name="name")
    val name: String,
    @Json(name="image")
    val image: String,
    @Json(name="species")
    val species: String,
    @Json(name="gender")
    val gender: String,
    @Json(name="url")
    val url: String
)

data class CharacterResponse(
    @Json(name="results")
    val results: List<Character>
)