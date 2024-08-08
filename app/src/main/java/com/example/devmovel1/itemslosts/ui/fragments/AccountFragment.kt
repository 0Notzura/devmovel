package com.example.devmovel1.itemslosts.ui.fragments

import android.accounts.Account
import android.app.Application
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.bumptech.glide.Glide
import com.example.devmovel1.BaseActivity
import com.example.devmovel1.R
import com.example.devmovel1.databinding.FragmentAccountPageBinding
import com.example.devmovel1.databinding.FragmentLostsBinding
import com.example.devmovel1.itemslosts.adapters.LostItemAdapter
import com.example.devmovel1.itemslosts.models.AccountData
import com.example.devmovel1.itemslosts.services.RetrofitInstance
import com.example.devmovel1.itemslosts.services.SessionManager
import com.example.itemslosts.models.LostItem
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class AccountFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
//    private val context = requireContext()

    private var account = AccountData("", "", "", "", "")

    private var _binding: FragmentAccountPageBinding? = null
//    private lateinit var adapter: LostItemAdapter
    private val binding get() = _binding!!
    private val lostItems = mutableListOf<LostItem>() // Lista de itens perdidos

    data class UserPayload(
        val user: UserAux,
        val iat: Int,
        val exp: Int
    )

    data class UserAux(
        val id: String
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val context = requireContext()
        sessionManager = SessionManager(context)

        // Infla o layout do fragmento
        _binding = FragmentAccountPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            // Configura o RecyclerView
            binding.accountRecyclerHeadlines.layoutManager = LinearLayoutManager(requireContext())
//            val adapter = LostItemAdapter(lostItems)
            val adapter = LostItemAdapter(emptyList())
            binding.accountRecyclerHeadlines.adapter = adapter

            getAccountData()
            setAccountView(view, account)

            // Carrega os dados e atualiza o RecyclerView
            loadAccountLostPosts(adapter)   
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun getAccountData() {

        val token = sessionManager.fetchToken()

        // Decodificar o token
        val decodedJWT: DecodedJWT = JWT.decode(token)

        val user = decodedJWT.getClaim("user")

        val payloadJson = decodedJWT.payload.decodeToString()

        val objectMapper = jacksonObjectMapper()
        val userPayload: UserPayload = objectMapper.readValue(payloadJson)

        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.user2.getUserData(userPayload.user.id.toString())
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        account.name = response.body()!!.name
                        account.ra = response.body()!!.ra
                        account.email = response.body()!!.email
                        account.phoneNumber = response.body()!!.phoneNumber
                        account.photoUrl = response.body()!!.photoUrl
                    } else {
                        Toast.makeText(context, "Dados da conta inacessíveis no momento", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    val errorMessage = "Erro: ${e.message}"
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("AccountFragment", errorMessage)
                }
            }
        }
        job.join()
    }

    private fun setAccountView(view: View, account: AccountData) {

        var accountName: TextView = view.findViewById(R.id.accountName)
        var accountRA: TextView = view.findViewById(R.id.accountRA)
        var accountEmail: TextView = view.findViewById(R.id.accountEmail)
        var accountPhoneNumber: TextView = view.findViewById(R.id.accountPhone)
        var accountPhoto: ImageView = view.findViewById(R.id.accountPhoto)

        accountName.text = account.name
        accountRA.text = account.ra
        accountEmail.text = account.email
        accountPhoneNumber.text = account.phoneNumber
        Glide.with(requireContext()).load(account.photoUrl).into(accountPhoto)
    }

    private suspend fun loadAccountLostPosts(adapter: LostItemAdapter) {
        val job = lifecycleScope.launch {
            binding.accountPostsProgressBar.visibility = View.VISIBLE
            val response = try {
                RetrofitInstance.posts.getLostItems()
            } catch (e: IOException) {
                e.printStackTrace()
                binding.accountPostsProgressBar.visibility = View.GONE
                return@launch
            } catch (e: HttpException) {
                e.printStackTrace()
                binding.accountPostsProgressBar.visibility = View.GONE
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                lostItems.clear()
                lostItems.addAll(response.body()!!)
//                println(lostItems)
                adapter.updateData(lostItems)
                adapter.notifyDataSetChanged()
                binding.accountPostsProgressBar.visibility = View.GONE
            }
        }
        job.join()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun String.decodeToString(): String {
        val decodedBytes = java.util.Base64.getUrlDecoder().decode(this)
        return String(decodedBytes)
    }
}