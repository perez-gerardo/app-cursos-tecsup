package com.perez.evaluacion06.data.remote

import com.perez.evaluacion06.data.remote.dto.CursoDto
import retrofit2.http.GET

interface CursoApiService {
    @GET("cursos")
    suspend fun getCursos(): List<CursoDto>
}

