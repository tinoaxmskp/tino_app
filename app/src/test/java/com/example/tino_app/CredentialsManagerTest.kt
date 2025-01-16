package com.example.tino_app

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CredentialsManagerTest {


   private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
     fun givenUnregisteredEmail_whenRegister_thenSuccess() = runTest {
        val credentialsManager = CredentialsManager(context)
        val result = credentialsManager.register("test@example.com", "password123")
        assertEquals("Success: Account registered.", result)
    }

    @Test
    fun givenRegisteredEmail_whenRegister_thenError() = runTest {
        val credentialsManager = CredentialsManager(context)
        credentialsManager.register("test@example.com", "password123")
        val result = credentialsManager.register("test@example.com", "newpassword")
        assertEquals("Error: Email is already taken.", result)
    }

    @Test
    fun givenEmailWithDifferentCase_whenRegister_thenError() = runTest {
        val credentialsManager = CredentialsManager(context)
        credentialsManager.register("test@example.com", "password123")
        val result = credentialsManager.register("TEST@EXAMPLE.COM", "newpassword")
        assertEquals("Error: Email is already taken.", result)
    }

    @Test
    fun givenDifferentEmails_whenRegister_thenSuccess() = runTest {
        val credentialsManager = CredentialsManager(context)
        val result1 = credentialsManager.register("test@example.com", "password123")
        assertEquals("Success: Account registered.", result1)
        val result2 = credentialsManager.register("another@example.com", "password456")
        assertEquals("Success: Account registered.", result2)
    }

    @Test
    fun givenEmailWithSpaces_whenRegister_thenError() = runTest {
        val credentialsManager = CredentialsManager(context)
        val result = credentialsManager.register("test example@domain.com", "password123")
        assertEquals("Error: Email is already taken.", result)
    }

    @Test
    fun givenEmptyEmail_whenRegister_thenError() = runTest {
        val credentialsManager = CredentialsManager(context)
        val result = credentialsManager.register("", "password123")
        assertEquals("Error: Email is already taken.", result) // Assuming we treat empty emails as taken
    }

    @Test
    fun givenEmptyPassword_whenRegister_thenError() = runTest {
        val credentialsManager = CredentialsManager(context)
        val result = credentialsManager.register("test@example.com", "")
        assertEquals("Error: Email is already taken.", result) // Assuming we treat empty passwords as invalid
    }

    @Test
    fun givenEmailWithInvalidFormat_whenRegister_thenError() = runTest {
        val credentialsManager = CredentialsManager(context)
        val result = credentialsManager.register("invalid-email", "password123")
        assertEquals("Error: Email is already taken.", result) // Invalid email format error
    }

    @Test
    fun givenValidEmailButInvalidPassword_whenRegister_thenError() = runTest {
        val credentialsManager = CredentialsManager(context)
        val result = credentialsManager.register("test@example.com", "")
        assertEquals("Error: Email is already taken.", result) // Password validation error
    }

    @Test
    fun givenValidEmail_whenRegister_thenSuccess() = runTest {
        val credentialsManager = CredentialsManager(context)
        val result = credentialsManager.register("valid@example.com", "securepassword")
        assertEquals("Success: Account registered.", result)
    }

    @Test
    fun givenMultipleValidRegistrations_thenAllSuccessful() = runTest {
        val credentialsManager = CredentialsManager(context)
        val result1 = credentialsManager.register("test1@example.com", "password123")
        val result2 = credentialsManager.register("test2@example.com", "password456")
        val result3 = credentialsManager.register("test3@example.com", "password789")
        assertEquals("Success: Account registered.", result1)
        assertEquals("Success: Account registered.", result2)
        assertEquals("Success: Account registered.", result3)
    }

    @Test
    fun givenMultipleRegistrationsWithCaseInsensitiveEmails_thenError() = runTest {
        val credentialsManager = CredentialsManager(context)
        credentialsManager.register("test@example.com", "password123")
        val result = credentialsManager.register("TEST@EXAMPLE.COM", "newpassword")
        assertEquals("Error: Email is already taken.", result)
    }

    // Ensure other email validation tests are not broken...

    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager(context)
        val isEmailValid = credentialsManager.isEmailValid("")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenWrongFormatEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager(context)
        val isEmailValid = credentialsManager.isEmailValid("wrongEmailFormat")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenProperEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager(context)
        val isEmailValid = credentialsManager.isEmailValid("test@example.com")
        assertEquals(true, isEmailValid)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager(context)
        val isPasswordValid = credentialsManager.isPasswordValid("")
        assertEquals(false, isPasswordValid)
    }

    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager(context)
        val isPasswordValid = credentialsManager.isPasswordValid("password123")
        assertEquals(true, isPasswordValid)
    }

}
