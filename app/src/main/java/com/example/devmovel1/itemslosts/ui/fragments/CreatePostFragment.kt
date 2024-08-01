package com.example.devmovel1.itemslosts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.devmovel1.R
import com.example.devmovel1.itemslosts.services.PostsService
import com.example.devmovel1.itemslosts.services.SessionManager
import com.example.itemslosts.models.LostItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePostFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private var token = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager = SessionManager(requireContext())
        token = sessionManager.fetchToken().toString()

        val editTextTitle: EditText = view.findViewById(R.id.editTextTitle)
        val editTextDescription: EditText = view.findViewById(R.id.editTextDescription)
        val editTextContact: EditText = view.findViewById(R.id.editTextContact)
        val editTextUrl: EditText = view.findViewById(R.id.editTextUrl)
        val btnSubmitPost: Button = view.findViewById(R.id.btnSubmitPost)

        btnSubmitPost.setOnClickListener {
            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()
            val contact = editTextContact.text.toString()
            val url = editTextUrl.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty() && contact.isNotEmpty() && url.isNotEmpty()) {
                createPost(title, description, contact, url)
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createPost(title: String, description: String, contact: String, url: String) {
        val post = LostItem(title, description, contact, url)
        val apiService = PostsService.create()

        val call = apiService.createPost(token, post)

        call.enqueue(object : Callback<LostItem> {
            override fun onResponse(call: Call<LostItem>, response: Response<LostItem>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Post created successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LostItem>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
