package com.perez.evaluacion06.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CursoEntity::class], version = 1, exportSchema = false)
abstract class CursoDatabase : RoomDatabase() {
    abstract fun cursoDao(): CursoDao
}

