package com.example.tino_app

import java.util.Locale
import java.util.HashMap

class CredentialsManager {


    private val credentials: MutableMap<String, String> = HashMap()

    fun register(email: String, password: String): String {
        val normalizedEmail = email.lowercase(Locale.getDefault())
        if (credentials.containsKey(normalizedEmail)) {
            return "Error: Email is already taken."
        }
        credentials[normalizedEmail] = password
        return "Success: Account registered."
    }

    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}
