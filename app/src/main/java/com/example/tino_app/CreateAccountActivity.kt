package com.example.tino_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tino_app.R.id.errorTextView

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var credentialsManager: CredentialsManager
    private lateinit var registerButton: Button
    private lateinit var alreadyMemberText: TextView
    private lateinit var loginButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        credentialsManager = CredentialsManager()

        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        registerButton = findViewById(R.id.nextButton)
        alreadyMemberText = findViewById(R.id.alreadyMemberText)
        loginButton = findViewById(R.id.loginButton)

        registerButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            val registrationResult = credentialsManager.register(email, password)

            if (registrationResult.startsWith("Success")) {
                // Registration successful, go back to login screen
                startActivity(Intent(this, LoginActivity::class.java))
                finish()  //  finish CreateAccountActivity to prevent returning back to it
            } else {
                // Show error message
                val errorTextView = findViewById<TextView>(errorTextView)
                errorTextView.text = registrationResult
            }
        }

        loginButton.setOnClickListener {
            // Redirect to the Login screen
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

