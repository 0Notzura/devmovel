package com.example.devmovel1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.devmovel1.itemslosts.ui.fragments.CreatePostFragment
import com.example.itemslosts.ui.fragments.LostsFragment
import com.example.itemslosts.ui.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Define o fragmento padrão quando a atividade é criada
        if (savedInstanceState == null) {
            loadFragment(LostsFragment())
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_losts -> {
                    loadFragment(LostsFragment())
                    true
                }
                R.id.fragment_search -> { // Certifique-se de que o ID aqui corresponde ao do menu
                    loadFragment(SearchFragment())
                    true
                }
                R.id.navigation_create_post -> {
                    loadFragment(CreatePostFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null) // Opcional: adiciona a transação ao back stack
        transaction.commit()
    }
}
