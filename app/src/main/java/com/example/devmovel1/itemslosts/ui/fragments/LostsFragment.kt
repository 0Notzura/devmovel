package com.example.itemslosts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devmovel1.databinding.FragmentLostsBinding
import com.example.devmovel1.itemslosts.adapters.LostItemAdapter
import com.example.devmovel1.itemslosts.services.RetrofitInstance
import com.example.itemslosts.models.LostItem
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LostsFragment : Fragment() {

    private var _binding: FragmentLostsBinding? = null
    private val binding get() = _binding!!
    private val lostItems = mutableListOf<LostItem>() // Lista de itens perdidos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura o RecyclerView
        binding.recyclerHeadlines.layoutManager = LinearLayoutManager(requireContext())
        val adapter = LostItemAdapter(lostItems)
        binding.recyclerHeadlines.adapter = adapter

        // Carrega os dados e atualiza o RecyclerView
        loadLostItems(adapter)
    }

    private fun loadLostItems(adapter: LostItemAdapter) {
        lifecycleScope.launch {
            binding.paginationProgressBar.visibility = View.VISIBLE
            val response = try {
                RetrofitInstance.api.getLostItems()
            } catch (e: IOException) {
                e.printStackTrace()
                binding.paginationProgressBar.visibility = View.GONE
                return@launch
            } catch (e: HttpException) {
                e.printStackTrace()
                binding.paginationProgressBar.visibility = View.GONE
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                lostItems.clear()
                lostItems.addAll(response.body()!!)
                adapter.notifyDataSetChanged()
                binding.paginationProgressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
