package com.aridev.cordero.starwarsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aridev.cordero.starwarsapp.core.dataApp.getDrawableImage
import com.aridev.cordero.starwarsapp.core.dataApp.setOnClickListenerBounce
import com.aridev.cordero.starwarsapp.data.Item
import com.aridev.cordero.starwarsapp.databinding.ItemHomeBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    var list : List<Item> = emptyList<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var actionCategory : ((item : Item) -> Unit)
    inner class  MyViewHolder(val binding : ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.apply {
            imgItem.setImageResource(imgItem.context.getDrawableImage(list[position].url!!))
            btnItem.setOnClickListenerBounce {
                actionCategory.invoke(list[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }
    override fun getItemCount() = list.size

    fun addItems(list : List<Item>) {
        val lastPosition = this.list.size - 1
        this.list += list
        notifyItemRangeInserted(lastPosition,list.size)
    }

    fun clearItems() {
        this.list = emptyList()
    }

    fun getItemPageSelected(position : Int) : String{
        return when{
            list[position].name != null && list[position].name != ""-> {list[position].name!! }
            list[position].title != null && list[position].title != ""-> {list[position].title!!}
            else -> ""
        }
    }
}