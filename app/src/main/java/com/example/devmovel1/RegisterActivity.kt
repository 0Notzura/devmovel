package com.example.devmovel1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.devmovel1.itemslosts.models.User
import com.example.devmovel1.itemslosts.models.User2
import com.example.devmovel1.itemslosts.services.RetrofitInstance
import com.example.devmovel1.itemslosts.services.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_register)
        sessionManager = SessionManager(this)

        val btnCadastrar: Button = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener { onRegister() }

        val btnGoToLogin: Button = findViewById(R.id.btnGoToLogin)
        btnGoToLogin.setOnClickListener { goToLogin() }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        finish()
        startActivity(intent)
    }
    private fun onRegister() {
        val txtRA: String = findViewById<EditText>(R.id.registerRA).text.toString()
        val txtPassword: String = findViewById<EditText>(R.id.registerPassword).text.toString()
        val txtName: String = findViewById<EditText>(R.id.registerName).text.toString()
        val txtEmail: String = findViewById<EditText>(R.id.registerEmail).text.toString()
        val txtPhoneNumber: String = findViewById<EditText>(R.id.registerPhoneNumber).text.toString()
        val txtPhotoUrl: String = findViewById<EditText>(R.id.registerPhotoUrl).text.toString()

        val userData = User2(txtRA, txtPassword, txtName, txtEmail, txtPhoneNumber, txtPhotoUrl)

        if (txtRA.trim().isEmpty() || txtPassword.trim().isEmpty()) {
            Toast.makeText(applicationContext, "RA e senha são campos obrigatórios", Toast.LENGTH_SHORT).show()
            return;
        }

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.auth2.register(userData)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        sessionManager.saveToken(response.body()!!.authToken)
                        finish()
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, "Aconteceu algum problema! Tente novamente mais tarde.", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    val errorMessage = "Erro: ${e.message}"
                    Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("RegisterFragment", errorMessage)
                }
            }
        }
    }
}
