package com.perez.evaluacion06.data.repository

import com.perez.evaluacion06.data.local.CursoDao
import com.perez.evaluacion06.data.local.toDomain
import com.perez.evaluacion06.data.remote.CursoApiService
import com.perez.evaluacion06.data.remote.dto.toEntity
import com.perez.evaluacion06.domain.model.Curso
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CursoRepository(
    private val api: CursoApiService,
    private val dao: CursoDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun observarCursos(): Flow<List<Curso>> =
        dao.getAll().map { entities -> entities.map { it.toDomain() } }

    suspend fun sincronizarCursos() = withContext(ioDispatcher) {
        val cursosRemotos = api.getCursos()
        dao.deleteAll()
        dao.insertAll(cursosRemotos.map { it.toEntity() })
    }
}

