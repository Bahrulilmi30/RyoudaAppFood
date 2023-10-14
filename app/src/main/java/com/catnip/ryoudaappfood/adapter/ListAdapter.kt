package com.catnip.ryuodaappfood.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.catnip.ryoudaappfood.R
import com.catnip.ryoudaappfood.databinding.ItemLinearMenuBinding
import com.catnip.ryuodaappfood.model.Item

class ListAdapter(
    private val item :ArrayList<Item>):
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    inner class ListViewHolder(val binding: ItemLinearMenuBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item) {
            Glide.with(itemView).load(item.image).into(binding.ivPictMenu)
            binding.tvTextMenu.text = item.name.toString()
            binding.tvHargaMenu.text = item.price.toString()
        }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ListViewHolder {
        val binding = ItemLinearMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(item[position])
        holder.binding.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("menu", item[position].name.toString())
            bundle.putInt("imeage", item[position].image.toInt())
            bundle.putInt("harga", item[position].price)
            bundle.putString("keterangan", item[position].desc)
            bundle.putString("lokasi", item[position].location)
            Navigation.findNavController(it).navigate(
                R.id.action_home_fragment_to_detailFragment,
                bundle
            )
        }
    }
}
