package com.example.devmovel1.itemslosts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.devmovel1.R
import com.example.devmovel1.itemslosts.models.AccountData
import com.example.itemslosts.models.LostItem

//class AccountDataAdapter (private val accountsData: List<AccountData>) : RecyclerView.Adapter<AccountDataAdapter.AccountDataViewHolder>() {
class AccountDataAdapter (private val accountsData: AccountData) : RecyclerView.Adapter<AccountDataAdapter.AccountDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_account, parent, false)
        return AccountDataViewHolder(view)
    }

//    override fun onBindViewHolder(holder: AccountDataViewHolder, position: Int) {
//        val accountData = accountsData[position]
//        holder.bind(accountData)
//    }

    override fun onBindViewHolder(holder: AccountDataViewHolder, position: Int) {
        val accountData = accountsData
        holder.bind(accountData)
    }

//    override fun getItemCount(): Int = accountsData.size

    override fun getItemCount(): Int = 1

    class AccountDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val accountPhoto: ImageView = itemView.findViewById(R.id.accountPhoto)
        private val accountName: TextView = itemView.findViewById(R.id.accountName)
        private val accountAge: TextView = itemView.findViewById(R.id.accountAge)
        private val accountEmail: TextView = itemView.findViewById(R.id.accountEmail)
        private val accountPhone: TextView = itemView.findViewById(R.id.accountPhone)

        fun bind(accountData: AccountData) {
            accountPhoto.setImageResource(accountData.imageResId)
            accountName.text = accountData.name
            accountAge.text = accountData.age.toString()
            accountEmail.text = accountData.email
            accountPhone.text = accountData.phoneNumber
        }
    }
}