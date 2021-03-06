package com.example.desafio_android_william_santos.presentation.Hq

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.presentation.base.BaseActivity
import com.example.desafio_android_william_santos.repository.hq.HqApiDataSource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hq_details.*
import java.text.NumberFormat
import java.util.*

class HqDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hq_details)

        setupToolbar(include3 as Toolbar,R.string.hq_details_title,true)

        val idCharacter:Int = intent.getIntExtra(ID_CHARACTER,0);

        val viewModel = HQViewModel.ViewModelFactory(HqApiDataSource()).create(HQViewModel::class.java)

        viewModel.hqLiveData.observe(this, Observer {
            it.let {hq ->
                hqTitle.text = hq.title
                hqDescription.text = hq.description
                val locale = Locale.US
                hqPrice.text = NumberFormat.getCurrencyInstance(locale).format(hq.price)
                Picasso.get().load(Uri.parse(hq.img)).into(hqImg)
            }
        })


        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperHq.displayedChild = viewFlipper.first
                viewFlipper.second?.let {errorMenssageId ->
                    characterTextViewError.text = getString(errorMenssageId)
                }
            }
        })

        viewModel.getHqHighestValue(idCharacter)
    }

    companion object{
        private const val ID_CHARACTER = "ID_CHARACTER"
        fun getStartIntent(context: Context,id:Int): Intent {
            return Intent(context,HqDetailsActivity::class.java).apply {
                putExtra(ID_CHARACTER,id)
            }
        }
    }
}
