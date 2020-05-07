package com.example.desafio_android_william_santos.presentation.characters

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.presentation.base.BaseActivity
import com.example.desafio_android_william_santos.presentation.details.CharacterDetailsActivity
import com.example.desafio_android_william_santos.repository.characters.CharactersApiDataSource
import kotlinx.android.synthetic.main.activity_characters.*


class CharactersActivity : BaseActivity() {

    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var page = 1;
    private val limit = 20;
    private var lastPos = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        var toolbar: Toolbar = findViewById(R.id.include)
        setupToolbar(toolbar , R.string.charaters_title)

        val viewModel:CharactersViewModel = CharactersViewModel.ViewModelFactory(
            CharactersApiDataSource()
        )
            .create(CharactersViewModel::class.java)

        viewModel.characersLiveData.observe(this, Observer {
            it?.let {heros ->
                with(recyclerCharacters){
                    layoutManager = LinearLayoutManager(this@CharactersActivity,RecyclerView.VERTICAL,false);
                    setHasFixedSize(true)
                    adapter = CharactersAdapter(heros){character ->
                        val intent = CharacterDetailsActivity.getStartIntent(this@CharactersActivity,character);

                        var img:View    =  recyclerCharacters.findViewById<View>(R.id.heroImg)
                        var name:View = recyclerCharacters.findViewById<View>(R.id.heroName)

                        for(position in recyclerCharacters.touchables){
                            if(position.findViewById<TextView>(R.id.heroName).text == character.name){
                                img =  position.findViewById<View>(R.id.heroImg)
                                name = position.findViewById<View>(R.id.heroName)
                            }
                        }

                        val activityOptions: ActivityOptionsCompat =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(
                                this@CharactersActivity,
                                Pair.create<View,String>(img,CharacterDetailsActivity.VIEW_NAME_HEADER_IMAGE),
                                Pair.create<View,String>(name,CharacterDetailsActivity.VIEW_NAME_HEADER_TITLE)
                            )

                        this@CharactersActivity.startActivity(intent,activityOptions.toBundle())

                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperCharacter.displayedChild = viewFlipper.first
                viewFlipper.second?.let {errorMenssageId ->
                    characterTextViewError.text = getString(errorMenssageId)
                }
                if(viewFlipper.second == null){
                    if(lastPos !== 0) {
                        recyclerCharacters.layoutManager?.scrollToPosition(lastPos - 4)
                        ItemsLoading.visibility = View.GONE
                    }
                }
            }
        })

        viewModel.getCharacter();


        setRecyclerViewScrollListener(viewModel)
    }


    private fun setRecyclerViewScrollListener(viewModel: CharactersViewModel) {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lm = recyclerView.layoutManager as LinearLayoutManager
                lastPos = lm.findLastVisibleItemPosition()
                val totalItemCount = recyclerView!!.layoutManager!!.itemCount
                if (totalItemCount <= lastPos+3) {
                   viewModel.getCharacter(limit*page,limit)
                    ItemsLoading.visibility = View.VISIBLE
                    page += 1
                }
            }
        }
        recyclerCharacters.addOnScrollListener(scrollListener)
    }

}
