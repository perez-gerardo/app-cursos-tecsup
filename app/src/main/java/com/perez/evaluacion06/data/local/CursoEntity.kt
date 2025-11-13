package com.perez.evaluacion06.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.perez.evaluacion06.domain.model.Curso

@Entity(tableName = "cursos")
data class CursoEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val docente: String,
    val creditos: Int,
    val ciclo: String
)

fun CursoEntity.toDomain() = Curso(
    id = id,
    nombre = nombre,
    docente = docente,
    creditos = creditos,
    ciclo = ciclo
)

