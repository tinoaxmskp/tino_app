package com.example.tino_app

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AppCompatActivity

class CreateAccountActivity : AppCompatActivity() {

    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        // Verify all IDs match the layout file
        val emailInputLayout by lazy { findViewById<TextInputLayout>(R.id.emailInputLayout) }
        val passwordInputLayout by lazy { findViewById<TextInputLayout>(R.id.passwordInputLayout) }
        val emailEditText by lazy { findViewById<TextInputEditText>(R.id.emailEditText) }
        val passwordEditText by lazy { findViewById<TextInputEditText>(R.id.passwordEditText)}
        val nextButton by lazy { findViewById<Button>(R.id.nextButton) }


        nextButton?.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()
            if (email.isEmpty()) {
                emailInputLayout.error = "Email cannot be empty"
                return@setOnClickListener
            } else {
                if (password.isEmpty()) {
                    passwordInputLayout.error = "Password cannot be empty"
                    return@setOnClickListener
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailInputLayout.error = "Invalid email format"
                    return@setOnClickListener
                }
            }
            // Attempt registration
            val registrationResult = credentialsManager.register(email ?: "", password ?: "")
            if (registrationResult) {
                // Successful registration
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                // Email already exists
                emailInputLayout?.error = "Email is already registered"
            }
        }
    }
}

