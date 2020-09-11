package com.example.proyectofinal.model.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectofinal.model.database.room.entidades.PeliculaEntidad

@Database(entities = [PeliculaEntidad::class], version = 1)
abstract class PeliculaDataBase : RoomDatabase() {

    abstract fun peliculaDao(): PeliculaDao

    companion object {

        @Volatile
        private var INSTANCIA: PeliculaDataBase? = null

        fun getPeliculaDatabase(context: Context): PeliculaDataBase {

            val instanciaTemporal = INSTANCIA
            if (instanciaTemporal != null) {
                return instanciaTemporal
            }
            synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    PeliculaDataBase::class.java,
                    "peliculas_database"
                ).build()
                INSTANCIA = instancia
                return instancia
            }
        }
    }
}