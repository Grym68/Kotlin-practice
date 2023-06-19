package co.uk.practice.retrofitexample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import co.uk.practice.retrofitexample.network.Character
import co.uk.practice.retrofitexample.network.CharacterResponse
import coil.load
import coil.transform.CircleCropTransformation
import retrofit2.Callback

class MainAdapter(
    private val context: Context,
    private val charactersList: List<Character>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(character: Character) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val image = itemView.findViewById<ImageView>(R.id.image)
            val species = itemView.findViewById<TextView>(R.id.species)
            val gender = itemView.findViewById<TextView>(R.id.gender)
            val profile = itemView.findViewById<Button>(R.id.character_profile)

            name.text = character.name
            species.text = character.species
            gender.text = character.gender
            profile.setOnClickListener {
                    gotoUrl(character.url);

            }
            image.load(character.image) {
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.rv_item, parent, false))
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(charactersList[position])
    }

    public fun gotoUrl(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri
        context.startActivity(intent)
    }
}