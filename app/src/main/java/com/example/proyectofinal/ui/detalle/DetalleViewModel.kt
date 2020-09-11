package com.example.proyectofinal.ui.detalle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.app.PeliculaApplication
import com.example.proyectofinal.model.Pelicula
import com.example.proyectofinal.model.repositorios.PeliculaRepositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetalleViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext = Dispatchers.Main + job

    private val peliculaRepositorio = PeliculaRepositorio(PeliculaApplication.baseDatos.peliculaDao())

    val _peliculaDetallada = MutableLiveData<Pelicula>()

    val peliculaDetallada: LiveData<Pelicula> = _peliculaDetallada

    fun encolarPelicula(pelicula: Pelicula){
        launch {
            peliculaRepositorio.guardarPelicula(pelicula)
        }
    }
}