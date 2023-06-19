package co.uk.practice.retrofitexample

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import co.uk.practice.retrofitexample.network.Character
import co.uk.practice.retrofitexample.network.CharacterResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Response


// the retrofit tutorial
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         viewModel.characterLiveData.observe(this) { state ->
             processCharacterResponse(state)
         }
    }


    private fun processCharacterResponse(state: ScreenState<List<Character>?>) {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        when(state) {
            is ScreenState.Loading -> {
                // TODO Add progress bar
                progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                progressBar.visibility = View.GONE
                if(state.data != null) {
                    val adapter = MainAdapter(this,state.data)
                    val recyclerView = findViewById<RecyclerView>(R.id.charactersRv)
                    recyclerView?.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView.adapter = adapter
                }
            }
            is ScreenState.Error -> {
                progressBar.visibility = View.GONE
                val view = progressBar.rootView
                Snackbar.make(view,state.message!!, Snackbar.LENGTH_LONG).show()
                // TODO Display the error
            }
        }
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