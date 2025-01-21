package com.example.tino_app

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class CreateAccountFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_create_account, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View Bindings
        val fullnameEditText = view.findViewById<EditText>(R.id.fullNameInput)
        val emailEditText = view.findViewById<EditText>(R.id.emailInputLayout)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordInputLayout)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        // Next button logic
        nextButton.setOnClickListener {
            val fullname = fullnameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Input validation
            if (fullname.isEmpty()) {
                fullnameEditText.error = "Full name cannot be empty"
                return@setOnClickListener
            }
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.error = "Enter a valid email"
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 8) {
                passwordEditText.error = "Password must be at least 8 characters"
                return@setOnClickListener
            }

            // Register user
            val credentialsManager = CredentialsManager(requireContext())
            lifecycleScope.launch {
                val result = credentialsManager.register(email, password)
                if (result) {
                    viewModel.setRegistrationComplete(true)
                    findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
                } else {
                    emailEditText.error = "Email already registered"
                }
            }
        }
    }
}
