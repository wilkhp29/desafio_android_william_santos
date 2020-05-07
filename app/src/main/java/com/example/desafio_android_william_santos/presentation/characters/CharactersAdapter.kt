package com.example.desafio_android_william_santos.presentation.characters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_william_santos.R
import com.example.dasafio_android_william_santos.data.model.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_characters.view.*

class CharactersAdapter(
    private val characters:List<Character>,
    val onItemClickListener: ((character:Character) -> Unit)
) : RecyclerView.Adapter<CharactersAdapter.HerosViwHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapter.HerosViwHolder {
        val viewType = LayoutInflater.from(parent.context).inflate(R.layout.item_characters,parent,false);
        return HerosViwHolder(viewType,onItemClickListener);
    }

    override fun getItemCount() = characters.count()

    override fun onBindViewHolder(holder: CharactersAdapter.HerosViwHolder, position: Int) {
        holder.bindView(characters[position])
    }

    class HerosViwHolder(view: View,private val onItemClickListener: ((character:Character) -> Unit)) : RecyclerView.ViewHolder(view){
        val img = view.heroImg;
        val name = view.heroName;

        fun bindView(character: Character){
            name.text = character.name
            Picasso.get().load(Uri.parse(character.img)).into(img)
            itemView.setOnClickListener{
                onItemClickListener.invoke(character)
            }
        }
    }
}