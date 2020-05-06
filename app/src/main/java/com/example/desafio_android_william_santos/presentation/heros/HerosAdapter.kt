package com.example.desafio_android_william_santos.presentation.heros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_william_santos.R
import com.example.dasafio_android_william_santos.data.model.Hero
import kotlinx.android.synthetic.main.item_heros.view.*

class HerosAdapter(
    val heros:List<Hero>
) : RecyclerView.Adapter<HerosAdapter.HerosViwHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HerosAdapter.HerosViwHolder {
        val viewType = LayoutInflater.from(parent.context).inflate(R.layout.item_heros,parent,false);
        return HerosViwHolder(viewType);
    }

    override fun getItemCount() = heros.count()

    override fun onBindViewHolder(holder: HerosAdapter.HerosViwHolder, position: Int) {
        holder.bindView(heros[position])
    }

    class HerosViwHolder(view: View) : RecyclerView.ViewHolder(view){
        val img = view.heroImg;
        val name = view.heroName;
        val description = view.heroDescription;
        fun bindView(hero:Hero){
            name.text = hero.name
            description.text = hero.description
        }
    }
}