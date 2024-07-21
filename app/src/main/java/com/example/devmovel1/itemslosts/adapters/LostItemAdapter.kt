package com.example.devmovel1.itemslosts.adapters

import com.example.devmovel1.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itemslosts.models.LostItem

class LostItemAdapter(private val lostItems: List<LostItem>) : RecyclerView.Adapter<LostItemAdapter.LostItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LostItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_lost, parent, false)
        return LostItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: LostItemViewHolder, position: Int) {
        val lostItem = lostItems[position]
        holder.bind(lostItem)
    }

    override fun getItemCount(): Int = lostItems.size

    class LostItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lostImage: ImageView = itemView.findViewById(R.id.lostImage)
        private val lostSource: TextView = itemView.findViewById(R.id.lostSource)
        private val lostTitle: TextView = itemView.findViewById(R.id.lostTitle)
        private val lostDescription: TextView = itemView.findViewById(R.id.lostDescription)
        private val lostDateTime: TextView = itemView.findViewById(R.id.lostDateTime)

        fun bind(lostItem: LostItem) {
            lostImage.setImageResource(lostItem.imageResId)
            lostSource.text = lostItem.source
            lostTitle.text = lostItem.title
            lostDescription.text = lostItem.description
            lostDateTime.text = lostItem.dateTime
        }
    }
}
