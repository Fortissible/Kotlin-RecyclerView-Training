package com.example.recycleviewtraining

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodsAdapter(private val foods: ArrayList<Foods>): RecyclerView.Adapter<FoodsAdapter.ViewHolder>() {
    //callback untuk aksi klik item pada RecyclerView
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    //inisialisasi metadata dari item si RecyclerView pakai ViewHolder
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_food_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_food_name)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_food_price)
    }

    //init ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    // bind data item ke item RecyclerView per posisi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        holder.imgPhoto.setImageResource(food.pic)
        holder.tvName.text = food.name
        holder.tvPrice.text = food.price

        // callback aksi klik pada item RecyclerView
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(foods[holder.adapterPosition]) }
    }

    //set jumlah item pada RecyclerView
    override fun getItemCount(): Int = foods.size

    // interface dari callback aksi klik pada item RecyclerView
    interface OnItemClickCallback {
        fun onItemClicked(data: Foods)
    }

}