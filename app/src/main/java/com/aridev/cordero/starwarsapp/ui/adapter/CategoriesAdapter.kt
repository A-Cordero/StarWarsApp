package com.aridev.cordero.starwarsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aridev.cordero.starwarsapp.R
import com.aridev.cordero.starwarsapp.data.model.Categories
import com.aridev.cordero.starwarsapp.data.model.Category
import com.aridev.cordero.starwarsapp.databinding.ItemCategoriesBinding

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {
    var list = emptyList<Category>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var actionCategory : ((position : Int, direction : Int, size : Int, category : Categories) -> Unit)
    private var selectedPosition  = 0
    inner class  MyViewHolder(val binding : ItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.apply {
            textCategory.text = list[position].type.value.lowercase()
            imgIconCategory.setImageResource(list[position].type.icon)

            when(list[position].status) {
                true -> {
                    containerCategory.setBackgroundResource(R.drawable.button_selected)
                    selectedPosition = position
                }
                false -> containerCategory.setBackgroundResource(R.drawable.button_unselected)
            }

            containerCategory.setOnClickListener {
                actionCategory.invoke(position ,  position - selectedPosition, list.size, list[position].type)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }
    override fun getItemCount() = list.size
}