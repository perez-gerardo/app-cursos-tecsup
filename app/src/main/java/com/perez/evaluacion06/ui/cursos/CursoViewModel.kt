package com.perez.evaluacion06.ui.cursos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.perez.evaluacion06.data.repository.CursoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CursoViewModel(
    private val repository: CursoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CursosUiState())
    val uiState: StateFlow<CursosUiState> = _uiState.asStateFlow()

    init {
        observarCursosLocales()
    }

    private fun observarCursosLocales() {
        viewModelScope.launch {
            repository.observarCursos().collect { cursos ->
                _uiState.update { current ->
                    current.copy(cursos = cursos)
                }
            }
        }
    }

    fun sincronizarCursos() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, successMessage = null, errorMessage = null) }
            try {
                repository.sincronizarCursos()
                _uiState.update { it.copy(isLoading = false, successMessage = "Cursos actualizados") }
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Error al sincronizar: ${exception.localizedMessage ?: "desconocido"}"
                    )
                }
            }
        }
    }

    fun clearMessages() {
        _uiState.update { it.copy(successMessage = null, errorMessage = null) }
    }
}

class CursoViewModelFactory(
    private val repository: CursoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CursoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CursoViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida: ${modelClass.name}")
    }
}

