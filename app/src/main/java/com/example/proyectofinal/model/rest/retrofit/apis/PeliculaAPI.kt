package com.example.proyectofinal.model.rest.retrofit.apis

import com.example.proyectofinal.API_KEY_THE_MOVIE_DB_DOT_ORG
import com.example.proyectofinal.model.PeliculaAPIConfiguration
import com.example.proyectofinal.model.RespuestaPelicula
import retrofit2.Call
import retrofit2.http.GET

interface PeliculaApi {

    @GET("3/discover/movie?api_key=$API_KEY_THE_MOVIE_DB_DOT_ORG")
    fun getRecomendaciones(): Call<RespuestaPelicula>

    @GET("3/configuration?api_key=$API_KEY_THE_MOVIE_DB_DOT_ORG")
    fun getConfiguracion(): Call<PeliculaAPIConfiguration>
}