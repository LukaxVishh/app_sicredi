package com.sicredi.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.sicredi.onboarding.data.model.Associate
import com.sicredi.onboarding.data.repository.AssociateRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class AssociateViewModel(private val repo: AssociateRepository) : ViewModel() {

    data class UiState(
        val items: List<Associate> = emptyList(),
        val loading: Boolean = false
    )

    val uiState: StateFlow<UiState> = repo.observeAll()
        .map { UiState(items = it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, UiState(loading = true))

    fun load(id: Long, onLoaded: (Associate?) -> Unit) = viewModelScope.launch {
        onLoaded(repo.get(id))
    }

    fun save(
        id: Long?,
        nome: String,
        cpf: String,
        email: String?,
        telefone: String?,
        endereco: String?,
        renda: Double?,
        nascimento: LocalDate?
    ) = viewModelScope.launch {
        if (id == null) {
            repo.add(
                Associate(
                    nome = nome,
                    cpf = cpf,
                    email = email,
                    telefone = telefone,
                    endereco = endereco,
                    rendaMensal = renda,
                    dataNascimento = nascimento
                )
            )
        } else {
            val existing = repo.get(id) ?: return@launch
            repo.update(
                existing.copy(
                    nome = nome,
                    cpf = cpf,
                    email = email,
                    telefone = telefone,
                    endereco = endereco,
                    rendaMensal = renda,
                    dataNascimento = nascimento
                )
            )
        }
    }

    fun delete(associate: Associate) = viewModelScope.launch {
        repo.delete(associate)
    }
}

class AssociateViewModelFactory(private val repo: AssociateRepository) : ViewModelProvider.Factory {

    // API atual (Lifecycle 2.8+): usa CreationExtras
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(AssociateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssociateViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    // Compatibilidade com chamadas antigas (se alguma task ainda usar)
    @Suppress("DEPRECATION")
    @Deprecated("Use create(modelClass, extras)")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssociateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssociateViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
