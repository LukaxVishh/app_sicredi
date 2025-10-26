package com.sicredi.onboarding.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LoginViewModel : ViewModel() {
    private val _isLogged = MutableStateFlow(false)
    val isLogged: StateFlow<Boolean> = _isLogged


    fun login(username: String, password: String) {
// MVP: qualquer combinação faz login (sem validação)
        _isLogged.value = true
    }
}