package com.example.challengerecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengerecyclerview.databinding.RecyclerViewItemBinding

class RecyclerViewAdapter(private val heroesData : ArrayList<Hero>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(var binding : RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = heroesData[position]
        holder.binding.rvItemNameTv.text = hero.name
        holder.binding.rvItemAsalDaerahTv.text = hero.asal
        holder.binding.rvItemBirthDeathTv.text = hero.date_birth_death
        holder.binding.rvItemImageview.setImageResource(hero.pic)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(heroesData[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = heroesData.size

    interface OnItemClickCallback{
        fun onItemClicked(data: Hero)
    }
}