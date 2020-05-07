package com.example.desafio_android_william_santos.presentation.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.example.dasafio_android_william_santos.data.model.Character
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.presentation.Hq.HqDetailsActivity
import com.example.desafio_android_william_santos.presentation.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_details.*
import kotlinx.android.synthetic.main.include_toolbar.*


class CharacterDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        var toolbar:Toolbar = findViewById(R.id.include2)
        setupToolbar(toolbar,R.string.charaters_details_title,true)

        val character = intent.getParcelableExtra<Character>(EXTRA_CHARACTER)
        detailsName.text = character?.name
        detailsDescription.text = character?.description
        Picasso.get().load(Uri.parse(character?.img)).into(detailsImg)

        ViewCompat.setTransitionName(detailsImg, VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(detailsName, VIEW_NAME_HEADER_TITLE);
        btnHq.setOnClickListener {
            val intent = HqDetailsActivity.getStartIntent(this@CharacterDetailsActivity,character.id)
            startActivity(intent)
        }
    }


    companion object{
        private const val EXTRA_CHARACTER   =   "EXTRA_CHARACTER"
        const val VIEW_NAME_HEADER_IMAGE    =   "VIEW_NAME_HEADER_IMAGE"
        const val VIEW_NAME_HEADER_TITLE    =   "VIEW_NAME_HEADER_TITLE"
        fun getStartIntent(context: Context, character:Character): Intent {
            return Intent(context,CharacterDetailsActivity::class.java).apply {
                putExtra(EXTRA_CHARACTER,character)
            }
        }
    }
}
