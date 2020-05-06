package com.example.desafio_android_william_santos.presentation.heros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_william_santos.R
import kotlinx.android.synthetic.main.activity_heros.*

class HerosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heros)

        toolbarMain.title = getString(R.string.heros_title)
        setSupportActionBar(toolbarMain)

        val viewModel:HerosViewModel = ViewModelProviders.of(this).get(HerosViewModel::class.java)

        viewModel.herosLiveData.observe(this, Observer {
            it?.let {heros ->
                with(recyclerHeros){
                    layoutManager = LinearLayoutManager(this@HerosActivity,RecyclerView.VERTICAL,false);
                    setHasFixedSize(true)
                    adapter = HerosAdapter(heros)
                }
            }
        })

        viewModel.getHeros();

    }


}
