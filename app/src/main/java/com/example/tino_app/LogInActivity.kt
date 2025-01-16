// LoginActivity.kt
package com.example.tino_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity() {

    private val validEmail = "test@te.st"
    private val validPassword = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        // View Bindings
        val emailInputLayout by lazy { findViewById<TextInputLayout>(R.id.emailInputLayout) }
        val passwordInputLayout by lazy { findViewById<TextInputLayout>(R.id.passwordInputLayout) }
        val emailEditText by lazy { findViewById<TextInputEditText>(R.id.emailEditText) }
        val passwordEditText by lazy { findViewById<TextInputEditText>(R.id.passwordEditText) }
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerNowText by lazy { findViewById<TextView>(R.id.registerButton) }


        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            when {
                email.isEmpty() -> {
                    emailInputLayout.error = "Email cannot be empty"
                }

                !isValidEmail(email) -> {
                    emailInputLayout.error = "Invalid email format"
                }

                email.equals(validEmail, ignoreCase = true).not() -> {
                    emailInputLayout.error = "Email does not exist"
                }

                else -> emailInputLayout.error = null
            }

            when {
                password.isEmpty() -> {
                    passwordInputLayout.error = "Password cannot be empty"
                }

                password != validPassword -> {
                    passwordInputLayout.error = "Incorrect password"
                }

                else -> passwordInputLayout.error = null
            }

            if (email.equals(validEmail, ignoreCase = true) && password == validPassword) {
                emailInputLayout.error = null
                passwordInputLayout.error = null
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


        // Register Now Button Listener
        registerNowText.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
