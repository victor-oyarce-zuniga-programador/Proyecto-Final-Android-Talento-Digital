package com.example.proyectofinal.model.rest.retrofit

import com.example.proyectofinal.model.rest.retrofit.apis.PeliculaApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClienteRetrofit {

    companion object {

        private const val BASE_URL = "https://api.themoviedb.org"

        fun instanciaRetrofit(): PeliculaApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PeliculaApi::class.java)
        }
    }
}