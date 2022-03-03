package com.aridev.cordero.starwarsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aridev.cordero.starwarsapp.core.dataApp.getDrawableImage
import com.aridev.cordero.starwarsapp.data.Item
import com.aridev.cordero.starwarsapp.databinding.ItemItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
    var list = emptyList<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var actionSearch : ((item : Item) -> Unit)
    inner class  MyViewHolder(val binding : ItemItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.apply {
            imgItem.setImageResource(imgItem.context.getDrawableImage(list[position].url!!))
            imgItem.setOnClickListener { actionSearch.invoke(list[position]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }
    override fun getItemCount() = list.size

}