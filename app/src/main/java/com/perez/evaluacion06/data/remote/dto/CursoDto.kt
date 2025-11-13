package com.perez.evaluacion06.data.remote.dto

import com.perez.evaluacion06.data.local.CursoEntity

data class CursoDto(
    val id: Int,
    val nombre: String,
    val docente: String,
    val creditos: Int,
    val ciclo: String
)

fun CursoDto.toEntity() = CursoEntity(
    id = id,
    nombre = nombre,
    docente = docente,
    creditos = creditos,
    ciclo = ciclo
)

