package com.example.proyectofinal.ui.resenadas

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.app.PeliculaApplication
import com.example.proyectofinal.model.Pelicula
import com.example.proyectofinal.model.repositorios.PeliculaRepositorio

class ResenadasViewModel : ViewModel() {

    private val peliculaRepositorio = PeliculaRepositorio(PeliculaApplication.baseDatos.peliculaDao())

    val listaResenadas: LiveData<List<Pelicula>> = peliculaRepositorio.peliculasResenadasTodas
}