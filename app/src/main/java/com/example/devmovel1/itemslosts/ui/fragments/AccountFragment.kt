package com.example.devmovel1.itemslosts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devmovel1.R
import com.example.devmovel1.databinding.FragmentLostsBinding
import com.example.devmovel1.itemslosts.adapters.LostItemAdapter
import com.example.devmovel1.itemslosts.models.AccountData
import com.example.devmovel1.itemslosts.services.RetrofitInstance
import com.example.itemslosts.models.LostItem
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AccountFragment : Fragment() {

//    private lateinit var recyclerView: RecyclerView
//    private lateinit var progressBar: ProgressBar
    //    private var accountData = mutableListOf<AccountData>()
//    private var account = mutableStateOf<AccountData>()
    private var account = AccountData("", 0, "", "", 0)

    private var _binding: FragmentLostsBinding? = null
    private val binding get() = _binding!!
    private val lostItems = mutableListOf<LostItem>() // Lista de itens perdidos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        _binding = FragmentLostsBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_account_page, container, false)
//        inflater.inflate(R.layout.fragment_account_page, container, false)
//        _binding = FragmentLostsBinding.inflate(inflater, container, false)
//        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var accountName: TextView = view.findViewById(R.id.accountName)
        var accountAge: TextView = view.findViewById(R.id.accountAge)
        var accountEmail: TextView = view.findViewById(R.id.accountEmail)
        var accountPhoneNumber: TextView = view.findViewById(R.id.accountPhone)
        var accountPhoto: ImageView = view.findViewById(R.id.accountPhoto)

        val account = getAccountData(0, account)
//        getAccountData(0, account)
        accountName.text = account.name
        accountAge.text = account.age.toString()
        accountEmail.text = account.email
        accountPhoneNumber.text = account.phoneNumber
        accountPhoto.setImageResource(account.imageResId)

        // Configura o RecyclerView
        binding.recyclerHeadlines.layoutManager = LinearLayoutManager(requireContext())
        val adapter = LostItemAdapter(lostItems)
        binding.recyclerHeadlines.adapter = adapter

        // Carrega os dados e atualiza o RecyclerView
        loadAccountLostPosts(0, adapter)
    }

    private fun getAccountData(id: Int, account: AccountData): AccountData {
        // Aqui você pode carregar dados reais. Exemplo estático para demonstração.
        val account = AccountData(
            "Fulano",
            25,
            "fulano@gmail.com",
            "+55 11 99887-7665",
            R.drawable.baseline_person_4_24)
        return account
    }

    private fun loadAccountLostPosts(id: Int, adapter: LostItemAdapter) {
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

}