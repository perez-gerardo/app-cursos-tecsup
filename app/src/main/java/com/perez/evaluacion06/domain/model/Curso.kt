package com.perez.evaluacion06.domain.model

data class Curso(
    val id: Int,
    val nombre: String,
    val docente: String,
    val creditos: Int,
    val ciclo: String
)

