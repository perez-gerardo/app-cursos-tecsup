package com.perez.evaluacion06

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.perez.evaluacion06.data.local.CursoDatabase
import com.perez.evaluacion06.data.local.CursoEntity
import com.perez.evaluacion06.data.remote.RetrofitClient
import com.perez.evaluacion06.data.repository.CursoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CursoApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.IO)

    val database: CursoDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CursoDatabase::class.java,
            "cursos.db"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                super.onCreate(db)
                applicationScope.launch {
                    val dao = database.cursoDao()
                    dao.insertAll(preloadedCursos())
                }
            }
        }).build()
    }

    private fun preloadedCursos(): List<CursoEntity> = listOf(
        CursoEntity(
            id = 101,
            nombre = "Algoritmos y Lógica de Programación",
            docente = "Patricia Salazar",
            creditos = 3,
            ciclo = "I"
        ),
        CursoEntity(
            id = 102,
            nombre = "Matemática Básica",
            docente = "Carlos Medina",
            creditos = 4,
            ciclo = "I"
        ),
        CursoEntity(
            id = 103,
            nombre = "Fundamentos de Redes",
            docente = "Gabriela Huarcaya",
            creditos = 3,
            ciclo = "II"
        ),
        CursoEntity(
            id = 104,
            nombre = "Programación Orientada a Objetos",
            docente = "Luis Sánchez",
            creditos = 4,
            ciclo = "III"
        )
    )

    val cursoRepository: CursoRepository by lazy {
        CursoRepository(
            api = RetrofitClient.cursoApiService,
            dao = database.cursoDao()
        )
    }
}

