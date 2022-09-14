package com.example.recycleviewtraining

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // lateinit var rvFoods untuk widget RecyclerView
    private lateinit var rvFoods : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inisialisasi RecyclerView dengan yang di XML
        rvFoods = findViewById(R.id.rv_food)
        rvFoods.setHasFixedSize(true)

        val foodsData = addDataList()
        showRecyclerView(foodsData)
    }

    @SuppressLint("Recycle")
    private fun addDataList(): ArrayList<Foods>{
        // Ambil data array dari resource values
        val listFoodsName = resources.getStringArray(R.array.data_name)
        val listFoodsPrice = resources.getStringArray(R.array.data_price)
        val listFoodsPic = resources.obtainTypedArray(R.array.data_pic)
        val listFoods = ArrayList<Foods>()
        for (i in listFoodsName.indices){
            listFoods.add(
                Foods(
                    listFoodsName[i],
                    listFoodsPrice[i],
                    listFoodsPic.getResourceId(i,-1)
                )
            )
        }
        return listFoods
    }

    private fun showRecyclerView(foodsData : ArrayList<Foods>){
        // set parameter dari RecyclerView
        val rvAdapter = FoodsAdapter(foodsData)
        rvFoods.layoutManager = LinearLayoutManager(this)
        rvFoods.adapter = rvAdapter

        // click callback biar bisa ngasih aksi pas click item di RecyclerView
        rvAdapter.setOnItemClickCallback(object : FoodsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Foods) {
                Toast.makeText(this@MainActivity,"Kamu telah memesan makanan : "+data.name+" seharga "+data.price,
                    Toast.LENGTH_SHORT).show()
                val intentToDetail = Intent(this@MainActivity,DetailFoodActivity::class.java)
                intentToDetail.putExtra(ITEM_KEY,data)
                startActivity(intentToDetail)
            }
        })
    }

    companion object {
        const val ITEM_KEY = "INTENT_ITEM"
    }
}