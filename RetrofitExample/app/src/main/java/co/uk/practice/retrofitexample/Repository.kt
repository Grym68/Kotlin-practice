package co.uk.practice.retrofitexample

import co.uk.practice.retrofitexample.network.ApiService

class Repository(private val apiService: ApiService) {
    fun getCharacter(page:String) = apiService.fetchCharacters(page)
}