package com.example.challengerecyclerview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challengerecyclerview.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var detailActivityBinding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivityBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)

        supportActionBar?.hide()

        val dataHero = intent.getParcelableExtra<Hero>(MainActivity.ITEM_ID)

        if (dataHero != null) {
            detailActivityBinding.detailAsalDaerahTv.text = dataHero.asal
            detailActivityBinding.detailBirthDeathTv.text = dataHero.date_birth_death
            detailActivityBinding.detailPhotoIv.setImageResource(dataHero.pic)
            detailActivityBinding.detailHeroDescTv.text = dataHero.desc
            detailActivityBinding.detailNameTv.text = dataHero.name


            detailActivityBinding.detailShareBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${dataHero.name}"))
                startActivity(intent)
            }
        }
    }
}