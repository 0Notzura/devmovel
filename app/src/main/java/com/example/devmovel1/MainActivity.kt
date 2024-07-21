package com.example.devmovel1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.itemslosts.ui.fragments.LostsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.devmovel1.R

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
                // Adicione aqui outros fragmentos para outras opções de navegação
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
