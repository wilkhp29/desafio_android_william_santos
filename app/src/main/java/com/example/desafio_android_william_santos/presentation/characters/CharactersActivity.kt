package com.example.desafio_android_william_santos.presentation.characters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_william_santos.R
import kotlinx.android.synthetic.main.activity_characters.*

class CharactersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        toolbarMain.title = getString(R.string.heros_title)
        setSupportActionBar(toolbarMain)

        val viewModel:CharactersViewModel = ViewModelProviders.of(this).get(CharactersViewModel::class.java)

        viewModel.characersLiveData.observe(this, Observer {
            it?.let {heros ->
                with(recyclerCharacters){
                    layoutManager = LinearLayoutManager(this@CharactersActivity,RecyclerView.VERTICAL,false);
                    setHasFixedSize(true)
                    adapter = CharactersAdapter(heros)
                }
            }
        })

        viewModel.getCharacter();

    }


}
