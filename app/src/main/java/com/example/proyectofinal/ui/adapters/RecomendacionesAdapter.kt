package com.example.proyectofinal.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.app.PeliculaApplication
import com.example.proyectofinal.model.Pelicula
import com.example.proyectofinal.ui.portada.RecomendacionesFragmentDirections
import com.squareup.picasso.Picasso

class RecomendacionesAdapter(val recomendaciones: MutableList<Pelicula>) : RecyclerView.Adapter<RecomendacionesAdapter.RecomendacionViewHolder>(){

    inner class RecomendacionViewHolder(vista: View) : RecyclerView.ViewHolder(vista){
        val fondoRecomendacion = vista.findViewById<ImageView>(R.id.fondoRecomendacion)
        val descripcionRecomendacion = vista.findViewById<TextView>(R.id.descripcionRecomendacion)

        init {
            vista.setOnClickListener {
                val posicion = adapterPosition
                val pelicula = recomendaciones.get(posicion)
                it.findNavController().navigate(RecomendacionesFragmentDirections.abrirDetalle(pelicula))
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecomendacionViewHolder {
        return RecomendacionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recomendacion, parent, false))    }

    override fun getItemCount(): Int {
        return recomendaciones.size
    }

    override fun onBindViewHolder(
        holder: RecomendacionViewHolder,
        position: Int
    ) {
        val pelicula = recomendaciones[position]
        val configuracion = PeliculaApplication.peliculaAPIConfiguration
        if(configuracion == null){
            Picasso.get().load(R.drawable.sin_conexion).into(holder.fondoRecomendacion)
        }else{
            val direccionCompleta = "${configuracion.images.secure_base_url}/${configuracion.images.backdrop_sizes.get(2)}/${pelicula.backdrop_path}"
            Picasso.get().load(direccionCompleta).into(holder.fondoRecomendacion)
        }
        holder.descripcionRecomendacion.text = pelicula.overview    }

    fun actualizarRecomendaciones(listaNueva: List<Pelicula>){
        recomendaciones.clear()
        recomendaciones.addAll(listaNueva)
        notifyDataSetChanged()
    }

}