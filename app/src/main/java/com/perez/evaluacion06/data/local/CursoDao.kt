package com.perez.evaluacion06.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CursoDao {

    @Query("SELECT * FROM cursos ORDER BY nombre ASC")
    fun getAll(): Flow<List<CursoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cursos: List<CursoEntity>)

    @Query("DELETE FROM cursos")
    suspend fun deleteAll()
}

