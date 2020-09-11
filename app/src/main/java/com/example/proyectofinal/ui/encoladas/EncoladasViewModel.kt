package com.example.proyectofinal.ui.encoladas

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.app.PeliculaApplication
import com.example.proyectofinal.model.Pelicula
import com.example.proyectofinal.model.repositorios.PeliculaRepositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EncoladasViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext = Dispatchers.Main + job

    private val peliculaRepositorio = PeliculaRepositorio(PeliculaApplication.baseDatos.peliculaDao())

    val listaEncoladas: LiveData<List<Pelicula>> = peliculaRepositorio.peliculasEncoladasTodas

    fun borrarEncoladasTodas(){
        launch {
            peliculaRepositorio.borrarPeliculasEncoladas()
        }
    }
}