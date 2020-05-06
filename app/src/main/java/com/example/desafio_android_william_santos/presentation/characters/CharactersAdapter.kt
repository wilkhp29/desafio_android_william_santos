package com.example.desafio_android_william_santos.presentation.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_william_santos.R
import com.example.dasafio_android_william_santos.data.model.Character
import kotlinx.android.synthetic.main.item_heros.view.*

class CharactersAdapter(
    val characters:List<Character>
) : RecyclerView.Adapter<CharactersAdapter.HerosViwHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapter.HerosViwHolder {
        val viewType = LayoutInflater.from(parent.context).inflate(R.layout.item_heros,parent,false);
        return HerosViwHolder(viewType);
    }

    override fun getItemCount() = characters.count()

    override fun onBindViewHolder(holder: CharactersAdapter.HerosViwHolder, position: Int) {
        holder.bindView(characters[position])
    }

    class HerosViwHolder(view: View) : RecyclerView.ViewHolder(view){
        val img = view.heroImg;
        val name = view.heroName;
        val description = view.heroDescription;
        fun bindView(character: Character){
            name.text = character.name
            description.text = character.description
        }
    }
}