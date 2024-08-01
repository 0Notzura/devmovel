package com.example.devmovel1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.devmovel1.itemslosts.models.User
import com.example.devmovel1.itemslosts.services.RetrofitInstance
import com.example.devmovel1.itemslosts.services.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        sessionManager = SessionManager(this)

        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener { onLogin() }

        val btnGoToRegister: Button = findViewById(R.id.btnGoToRegister)
        btnGoToRegister.setOnClickListener { goToRegister() }
    }

    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        finish()
        startActivity(intent)
    }

    private fun onLogin() {
        val txtRA: String = findViewById<EditText>(R.id.registerRA).text.toString()
        val txtPassword: String = findViewById<EditText>(R.id.registerPassword).text.toString()
        val userData = User(txtRA, txtPassword)

        if (txtRA.trim().isEmpty() || txtPassword.trim().isEmpty()) {
            Toast.makeText(applicationContext, "RA e senha são campos obrigatórios", Toast.LENGTH_SHORT).show()
            return;
        }

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.auth.login(userData)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        sessionManager.saveToken(response.body()!!.authToken)
                        finish()
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, "RA ou senha inválidos", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    val errorMessage = "Erro: ${e.message}"
                    Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("LoginFragment", errorMessage)
                }
            }
        }
    }
}
