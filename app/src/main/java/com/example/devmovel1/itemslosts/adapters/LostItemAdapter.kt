// LostItemAdapter.kt
package com.example.devmovel1.itemslosts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.devmovel1.databinding.FragmentLostBinding
import com.example.itemslosts.models.LostItem

class LostItemAdapter(private val lostItems: List<LostItem>) :
    RecyclerView.Adapter<LostItemAdapter.LostItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LostItemViewHolder {
        val binding = FragmentLostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LostItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LostItemViewHolder, position: Int) {
        val lostItem = lostItems[position]
        holder.bind(lostItem)
    }

    override fun getItemCount() = lostItems.size

    inner class LostItemViewHolder(private val binding: FragmentLostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lostItem: LostItem) {
            Glide.with(binding.root.context).load(lostItem.url).into(binding.lostImage)
            binding.lostSource.text = lostItem.contact
            binding.lostTitle.text = lostItem.title
            binding.lostDescription.text = lostItem.description
            binding.lostDateTime.text = "Some date" // Ajuste conforme necessário
        }
    }
}
