package co.uk.practice.retrofitexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.uk.practice.retrofitexample.network.ApiClient
import co.uk.practice.retrofitexample.network.Character
import co.uk.practice.retrofitexample.network.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository
= Repository(ApiClient.apiService)): ViewModel() {
    private var _charactersLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    val characterLiveData: LiveData<ScreenState<List<Character>?>>
        get() = _charactersLiveData

    init {
        fetchCharacter()
    }
    private fun fetchCharacter() {
        val client = repository.getCharacter("1")
        _charactersLiveData.postValue(ScreenState.Loading(null))
        client.enqueue(object: Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>) {
                if (response.isSuccessful){
                    // here we are going to do a screen state,
                    // to check if we are passing any data
                    _charactersLiveData.postValue(ScreenState.Success(response.body()?.results))
                } else {
                    _charactersLiveData.postValue(ScreenState.Error(response.code().toString(), null))
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
//                Log.d("Failure", t.message.toString())
                _charactersLiveData.postValue(ScreenState.Error(t.message.toString(), null))
            }

        })
    }
}