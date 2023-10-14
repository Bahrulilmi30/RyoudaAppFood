package com.catnip.ryoudaappfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catnip.ryoudaappfood.databinding.ItemConfirmBinding
import com.catnip.ryuodaappfood.data.local.database.entity.ItemEntity
import com.bumptech.glide.Glide

class ConfirmAdapter(
    private val itemClick: (ItemEntity) -> Unit,
    private val delete: (ItemEntity) -> Unit,
) : RecyclerView.Adapter<ConfirmAdapter.ConfirmViewHolder>() {

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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConfirmViewHolder {
        val binding =
            ItemConfirmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConfirmViewHolder(binding, itemClick, delete)
    }

    override fun onBindViewHolder(holder: ConfirmViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ConfirmViewHolder(
        private val binding: ItemConfirmBinding,
        val itemClick: (ItemEntity) -> Unit,
        val delete: (ItemEntity) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: ItemEntity) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                with(binding) {
                    ivDelete.setOnClickListener {
                        delete(item)
                    }
                    Glide.with(itemView).load(image).into(ivPictMenu)
                    tvConfirmHargaMenu.text = harga.toString()
                    tvConfirmNameMenu.text = name
                    tvSum.text = "x$stock"
                }
            }
        }
    }
}
