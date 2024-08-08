// LostItemAdapter.kt
package com.example.devmovel1.itemslosts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.devmovel1.databinding.FragmentAccountPageBinding
import com.example.devmovel1.databinding.FragmentLostBinding
import com.example.itemslosts.models.LostItem

class LostPostAdapter(private val lostItems: List<LostItem>) :
    RecyclerView.Adapter<LostPostAdapter.LostPostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LostPostViewHolder {
        val binding = FragmentLostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LostPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LostPostViewHolder, position: Int) {
        val lostItem = lostItems[position]
        holder.bind(lostItem)
    }

    override fun getItemCount() = lostItems.size

    inner class LostPostViewHolder(private val binding: FragmentLostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lostItem: LostItem) {
            Glide.with(binding.root.context).load(lostItem.url).into(binding.lostImage)
            binding.lostSource.text = lostItem.contact
            binding.lostTitle.text = lostItem.title
            binding.lostDescription.text = lostItem.description
        }
    }
}
