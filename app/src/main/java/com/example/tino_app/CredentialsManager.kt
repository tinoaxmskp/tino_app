// CredentialsManager.kt
package com.example.tino_app

class CredentialsManager {

    private val accounts = mutableMapOf<String, String>()

    init {
        // Adding a default account for testing
        accounts["test@te.st"] = "1234"
    }

    enum class LoginResult {
        SUCCESS,
        EMPTY_EMAIL,
        INVALID_EMAIL_FORMAT,
        INVALID_EMAIL,
        EMPTY_PASSWORD,
        INVALID_PASSWORD
    }

    fun login(email: String, password: String): LoginResult {
        if (email.isEmpty()) return LoginResult.EMPTY_EMAIL
        if (!isEmailValid(email)) return LoginResult.INVALID_EMAIL_FORMAT
        if (!accounts.containsKey(email.lowercase())) return LoginResult.INVALID_EMAIL
        if (password.isEmpty()) return LoginResult.EMPTY_PASSWORD
        if (accounts[email.lowercase()] != password) return LoginResult.INVALID_PASSWORD

        return LoginResult.SUCCESS
    }

    fun register(email: String, password: String): Boolean {
        if (accounts.containsKey(email.lowercase())) return false
        accounts[email.lowercase()] = password
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        // Example password validation: Minimum 6 characters
        return password.length >= 8
    }
}
