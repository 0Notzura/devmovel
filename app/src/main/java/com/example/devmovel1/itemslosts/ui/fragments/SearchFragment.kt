package com.example.itemslosts.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devmovel1.R
import com.example.devmovel1.itemslosts.adapters.LostItemAdapter
import com.example.devmovel1.itemslosts.services.RetrofitInstance
import com.example.itemslosts.models.LostItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var searchEdit: EditText
    private val lostItems = mutableListOf<LostItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerSearch)
        progressBar = view.findViewById(R.id.paginationProgressBar)
        searchEdit = view.findViewById(R.id.searchEdit)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = LostItemAdapter(lostItems)
        recyclerView.adapter = adapter

        // Adiciona um listener ao EditText para iniciar a busca quando o texto for alterado
        searchEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isNotEmpty()) {
                    searchLostItems(query, adapter)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun searchLostItems(query: String, adapter: LostItemAdapter) {
        progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.searchLostItems(query)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        lostItems.clear()
                        lostItems.addAll(response.body()!!)
                        adapter.notifyDataSetChanged()
                        Toast.makeText(requireContext(), "Itens encontrados", Toast.LENGTH_SHORT).show()
                    } else {
                        val errorMessage = "Erro ao buscar itens: ${response.message()}"
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                        Log.e("SearchFragment", errorMessage)
                    }
                    progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    val errorMessage = "Erro: ${e.message}"
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("SearchFragment", errorMessage)
                    progressBar.visibility = View.GONE
                }
            }
        }
    }


}
