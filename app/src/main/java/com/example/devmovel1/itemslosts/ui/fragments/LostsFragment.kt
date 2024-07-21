package com.example.itemslosts.ui.fragments


import com.example.devmovel1.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devmovel1.itemslosts.adapters.LostItemAdapter
import com.example.itemslosts.models.LostItem

class LostsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val lostItems = mutableListOf<LostItem>() // Lista de itens perdidos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        return inflater.inflate(R.layout.fragment_losts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerHeadlines)
        progressBar = view.findViewById(R.id.paginationProgressBar)

        // Configura o RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = LostItemAdapter(lostItems)
        recyclerView.adapter = adapter

        // Carrega os dados e atualiza o RecyclerView
        loadLostItems(adapter)
    }

    private fun loadLostItems(adapter: LostItemAdapter) {
        // Aqui você pode carregar dados reais. Exemplo estático para demonstração.
        lostItems.addAll(listOf(
            LostItem("Título 1", "Descrição 1", "Contato 1", "01/01/2024", R.drawable.baseline_person_4_24),
            LostItem("Título 2", "Descrição 2", "Contato 2", "02/01/2024", R.drawable.ic_launcher_foreground)
        ))
        adapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }
}
