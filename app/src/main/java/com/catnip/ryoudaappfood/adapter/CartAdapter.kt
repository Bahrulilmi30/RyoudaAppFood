package com.catnip.ryuodaappfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.catnip.ryoudaappfood.databinding.ItemCartBinding
import com.catnip.ryuodaappfood.data.local.database.entity.ItemEntity

class CartAdapter (
    private val itemClick: (ItemEntity) -> Unit,
    private val delete: (ItemEntity) -> Unit,
    private val decrement: (ItemEntity) -> Unit,
    private val increment: (ItemEntity) -> Unit,
): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var items: MutableList<ItemEntity> = mutableListOf()

    fun setItems(items: List<ItemEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<ItemEntity>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding, itemClick, delete, decrement, increment)
    }

    override fun getItemCount(): Int =items.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    class CartViewHolder(
        private val binding: ItemCartBinding,
        private val itemClick: (ItemEntity) -> Unit,
        private val delete: (ItemEntity) -> Unit,
        private val decrement: (ItemEntity) -> Unit,
        private val increment: (ItemEntity) -> Unit,
    ): RecyclerView.ViewHolder(binding.root){
        fun bindView(item: ItemEntity) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                with(binding) {
                    ivDelete.setOnClickListener {
                        delete(item)
                    }
                    ivPlus.setOnClickListener {
                        increment(item)
                    }
                    ivMinus.setOnClickListener {
                        decrement(item)
                    }
                    Glide.with(itemView).load(image).into(ivPictMenu)
                    tvHargaMenu.text = harga.toString()
                    tvTextMenu.text = name
                    tvSum.text = stock.toString()
                }
            }

        }
}
}