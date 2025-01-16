// converted from LoginActivity.kt
package com.example.tino_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]


        // Login button logic

        // Observe registration success
        viewModel.registrationComplete.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(requireContext(), "Account created successfully!", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // View Bindings
        val emailInputLayout = view.findViewById<TextInputLayout>(R.id.emailInputLayout)
        val passwordInputLayout = view.findViewById<TextInputLayout>(R.id.passwordInputLayout)
        val emailEditText =  view.findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordEditText =  view.findViewById<TextInputEditText>(R.id.passwordEditText)
        val loginButton =  view.findViewById<Button>(R.id.loginButton)
        val registerNowText =  view.findViewById<TextView>(R.id.registerButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            viewLifecycleOwner.lifecycleScope.launch {
                val credentialsManager = CredentialsManager(requireContext())
                val isValid = credentialsManager.login(email, password)

                if (isValid) {
                    Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    emailInputLayout.error = "Invalid credentials"
                    passwordInputLayout.error = "Invalid credentials"
                }
            }
        }



        // Navigate to CreateAccountFragment
        registerNowText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
        }

    }
}

