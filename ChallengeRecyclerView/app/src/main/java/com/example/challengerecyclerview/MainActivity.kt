package com.example.challengerecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengerecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        supportActionBar?.hide()

        mainActivityBinding.heroesRv.setHasFixedSize(true)

        val dataHeroes = getDataHeroes()
        showRecyclerView(dataHeroes)
    }

    private fun getDataHeroes(): ArrayList<Hero>{
        val heroListName = resources.getStringArray(R.array.data_name)
        val heroListDate = resources.getStringArray(R.array.data_birth_death)
        val heroListDesc = resources.getStringArray(R.array.data_description)
        val heroListAsal = resources.getStringArray(R.array.data_asal_daerah)
        val heroListPics = resources.obtainTypedArray(R.array.data_photo)
        val heroesData = ArrayList<Hero>()
        for (index in heroListName.indices){
            heroesData.add(
                Hero(
                name = heroListName[index],
                date_birth_death =  heroListDate[index],
                desc =  heroListDesc[index],
                asal = heroListAsal[index],
                heroListPics.getResourceId(index,-1)
                )
            )
        }
        return heroesData
    }

    private fun showRecyclerView(heroesData : ArrayList<Hero>){
        val adapter = RecyclerViewAdapter(heroesData)
        mainActivityBinding.heroesRv.adapter = adapter
        mainActivityBinding.heroesRv.layoutManager = LinearLayoutManager(this)

        adapter.setOnItemClickCallback(object : RecyclerViewAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                val intentToDetail = Intent(this@MainActivity,DetailActivity::class.java)
                intentToDetail.putExtra(ITEM_ID,data)
                startActivity(intentToDetail)
            }
        })
    }

    companion object{
        const val ITEM_ID = "ITEM_ID"
    }
}