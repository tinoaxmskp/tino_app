package com.example.tino_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _registrationComplete = MutableLiveData<Boolean>()
    val registrationComplete: LiveData<Boolean> get() = _registrationComplete

    fun setRegistrationComplete(value: Boolean) {
        _registrationComplete.value = value
    }
}
