package com.example.proyectofinal.model.repositorios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyectofinal.model.Pelicula
import com.example.proyectofinal.model.PeliculaAPIConfiguration
import com.example.proyectofinal.model.PeliculaEntidadMapper
import com.example.proyectofinal.model.RespuestaPelicula
import com.example.proyectofinal.model.database.room.PeliculaDao
import com.example.proyectofinal.model.rest.retrofit.ClienteRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeliculaRepositorio (val peliculaDao: PeliculaDao) {

    val peliculasResenadasTodas: LiveData<List<Pelicula>> = peliculaDao.getResenadas()
    val peliculasEncoladasTodas: LiveData<List<Pelicula>> = peliculaDao.getEncoladas()

    private val _recomendaciones = MutableLiveData<List<Pelicula>>()
    val recomendaciones: LiveData<List<Pelicula>> = _recomendaciones

    private val _errorConexionRecomendaciones = MutableLiveData<String>()
    val errorConexionRecomendaciones: LiveData<String> = _errorConexionRecomendaciones

    private val _configuracion = MutableLiveData<PeliculaAPIConfiguration>()
    val configuracion: LiveData<PeliculaAPIConfiguration> = _configuracion

    private val _errorConexionConfiguracion = MutableLiveData<String>()
    val errorConexionConfiguracion: LiveData<String> = _errorConexionConfiguracion

    val clienteRetrofit = ClienteRetrofit.instanciaRetrofit()

    suspend fun guardarPelicula(pelicula: Pelicula){
        withContext(Dispatchers.IO){
            val peliculaEntidad = PeliculaEntidadMapper.mapearDePeliculaAPeliculaEntidad(pelicula)
            peliculaDao.guardarPelicula(peliculaEntidad) }
    }

    suspend fun borrarPeliculaEncolada(peliculaEncolada: Pelicula){
        withContext(Dispatchers.IO){
            val peliculaEntidad = PeliculaEntidadMapper.mapearDePeliculaAPeliculaEntidad(peliculaEncolada)
            peliculaDao.borrarEncolada(peliculaEntidad) }
    }

    suspend fun borrarPeliculasEncoladas(){
        withContext(Dispatchers.IO){ peliculaDao.borrarEncoladasTodas() }
    }

    fun getRecomendaciones(){
        clienteRetrofit.getRecomendaciones().enqueue(object: Callback<RespuestaPelicula> {

            override fun onFailure(call: Call<RespuestaPelicula>, t: Throwable) {
                _errorConexionRecomendaciones.postValue(t.message)
            }

            override fun onResponse(
                call: Call<RespuestaPelicula>,
                response: Response<RespuestaPelicula>
            ) {
                val respuestaPelicula = response.body()
                if(respuestaPelicula != null){
                    _recomendaciones.postValue(respuestaPelicula.results)
                }
            }

        })
    }

    fun getConfiguracion(){
        clienteRetrofit.getConfiguracion().enqueue(object: Callback<PeliculaAPIConfiguration> {

            override fun onFailure(call: Call<PeliculaAPIConfiguration>, t: Throwable) {
                _errorConexionConfiguracion.postValue(t.message)
            }

            override fun onResponse(
                call: Call<PeliculaAPIConfiguration>,
                response: Response<PeliculaAPIConfiguration>
            ) {
                _configuracion.postValue(response.body())
            }
        })
    }
}