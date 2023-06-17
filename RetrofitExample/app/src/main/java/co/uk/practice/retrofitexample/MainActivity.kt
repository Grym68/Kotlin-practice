package co.uk.practice.retrofitexample

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import co.uk.practice.retrofitexample.network.ApiClient
import co.uk.practice.retrofitexample.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response


// the retrofit tutorial
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = ApiClient.apiService.fetchCharacters("1")


        client.enqueue(object : retrofit2.Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                if (response.isSuccessful) {
                    Log.d("characters", "" + response.body())

                    val result = response.body()?.results
                    result?.let {
                        val adapter = MainAdapter(result)
                        val recyclerView = findViewById<RecyclerView>(R.id.charactersRv)
                        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView?.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("failed", "" + t.message)
            }

        })
    }
}


// the test app from Intellij
//class MainActivity : AppCompatActivity() {
//    private var message: TextView? = null
//    private var counter = 0
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        message = findViewById(R.id.clickCounter)
//        val droid = findViewById<ImageView>(R.id.chuthuluImage)
//
//        //Define and attach click listener
//        droid.setOnClickListener { tapDroid() }
//    }
//
//    private fun tapDroid() {
//        counter++
//        val countAsText: String
//        /*
//        * In real applications you should not write switch like the one below.
//        * Use resource of type "Quantity strings (plurals)" instead.
//        * See https://developer.android.com/guide/topics/resources/string-resource#Plurals
//        */
//        countAsText = when (counter) {
//            1 -> "once"
//            2 -> "twice"
//            else -> String.format("%d times", counter)
//        }
//        message!!.text = String.format("You touched the droid %s", countAsText)
//    }
//}