// CredentialsManager.kt
package com.example.tino_app

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CredentialsManager(private val context: Context) {

    // Initialize UserDao with context
    val database = UserDatabase.getDatabase(context)
    private val userDao = database.userDao()
   // private val userDao: UserDao by lazy {
   //     UserDatabase.getDatabase(context).userDao()
   // }

    suspend fun register(email: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val existingUser = userDao.getUserByEmail(email.lowercase())
            if (existingUser == null) {
                userDao.insertUser(
                    User(email = email.lowercase(), password = password)
                )
                true
            } else {
                false
            }
        }
    }

    suspend fun login(email: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val user = userDao.getUserByEmail(email.lowercase())
            user?.password == password
        }
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }
}

