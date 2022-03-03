package com.aridev.cordero.starwarsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aridev.cordero.starwarsapp.core.dataApp.getDrawableImage
import com.aridev.cordero.starwarsapp.databinding.ItemSubDetailBinding

class SubItemAdapter : RecyclerView.Adapter<SubItemAdapter.MyViewHolder>() {
    var list = emptyList<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    lateinit var actionDetail : ((url : String)->Unit)
    inner class  MyViewHolder(val binding : ItemSubDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.apply {
            imgSubDetail.setImageResource(imgSubDetail.context.getDrawableImage(list[position]))
            imgSubDetail.setOnClickListener {
                actionDetail.invoke(list[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemSubDetailBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }
    override fun getItemCount() = list.size

}