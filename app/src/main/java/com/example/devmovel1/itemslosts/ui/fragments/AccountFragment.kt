package com.example.devmovel1.itemslosts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devmovel1.R
import com.example.devmovel1.itemslosts.adapters.AccountDataAdapter
import com.example.devmovel1.itemslosts.adapters.LostItemAdapter
import com.example.devmovel1.itemslosts.models.AccountData
import com.example.itemslosts.models.LostItem

class AccountFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
//    private var accountData = mutableListOf<AccountData>()  // Lista de itens perdidos
    private var accountData = AccountData("", 0, "", "", 0)  // Lista de itens perdidos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerHeadlines)
        progressBar = view.findViewById(R.id.paginationProgressBar)

        // Configura o RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = AccountDataAdapter(accountData)
        recyclerView.adapter = adapter

        // Carrega os dados e atualiza o RecyclerView
        loadAccountData(adapter)
    }

    private fun loadAccountData(adapter: AccountDataAdapter) {
        // Aqui você pode carregar dados reais. Exemplo estático para demonstração.
//        accountData.addAll(listOf(
//            AccountData("Fulano", 25, "fulano@gmail.com", "+55 11 99887-7665", R.drawable.baseline_person_4_24),
//        ))
        accountData.name = "Fulano"
        accountData.age = 25
        accountData.email = "fulano@gmail.com"
        accountData.phoneNumber = "+55 11 99887-7665"
        accountData.imageResId = R.drawable.baseline_person_4_24

        adapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }
}