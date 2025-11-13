package com.perez.evaluacion06.ui.cursos

import com.perez.evaluacion06.domain.model.Curso

data class CursosUiState(
    val cursos: List<Curso> = emptyList(),
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)

