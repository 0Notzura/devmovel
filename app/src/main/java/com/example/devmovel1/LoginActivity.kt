package com.example.devmovel1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

        val button: Button = findViewById<Button>(R.id.btnLogin)

        button.setOnClickListener { clickedLogin() }
    }

    private fun clickedLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}
