package com.example.proyectofinal.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.app.PeliculaApplication
import com.example.proyectofinal.model.Pelicula
import com.example.proyectofinal.model.repositorios.PeliculaRepositorio
import com.example.proyectofinal.ui.resenar.ResenaFragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EncoladasAdapter(
    val peliculasEncoladas: MutableList<Pelicula>,
    val peliculaRepositorio: PeliculaRepositorio =
        PeliculaRepositorio(PeliculaApplication.baseDatos.peliculaDao()))
    : RecyclerView.Adapter<EncoladasAdapter.EncoladaViewHolder>(), CoroutineScope{

    private val job = Job()
    override val coroutineContext = Dispatchers.Main + job

    inner class EncoladaViewHolder(vista: View) : RecyclerView.ViewHolder(vista){
        val fondoEncolada = vista.findViewById<ImageView>(R.id.fondoEncolada)
        val tituloEncolada = vista.findViewById<TextView>(R.id.tituloEncolada)
        val borrarEncolada = vista.findViewById<ImageView>(R.id.borrarEncolada)

        init {
            fondoEncolada.setOnClickListener { resenar(vista) }
            tituloEncolada.setOnClickListener { resenar(vista) }
        }

        private fun resenar(vista: View){
            val posicion = adapterPosition
            val pelicula = peliculasEncoladas.get(posicion)
            val resenaFragment = ResenaFragment.nuevaInstancia(pelicula)
            val contexto = vista.context as AppCompatActivity
            resenaFragment.show(contexto.supportFragmentManager, "ENCOLADAS_ADAPTER")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EncoladaViewHolder {
        return EncoladaViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_encolada, parent, false))    }

    override fun getItemCount(): Int {
        return peliculasEncoladas.size
    }

    override fun onBindViewHolder(
        holder: EncoladaViewHolder,
        position: Int
    ) {
        val pelicula = peliculasEncoladas[position]
        val configuracion = PeliculaApplication.peliculaAPIConfiguration
        if(configuracion == null){
            Picasso.get().load(R.drawable.sin_conexion).into(holder.fondoEncolada)
        }else{
            val direccionCompleta = "${configuracion.images.secure_base_url}/${configuracion.images.backdrop_sizes.get(0)}/${pelicula.backdrop_path}"
            Picasso.get().load(direccionCompleta).into(holder.fondoEncolada)
        }
        holder.tituloEncolada.text = pelicula.original_title
        holder.borrarEncolada.setOnClickListener {
            launch {
                peliculaRepositorio.borrarPeliculaEncolada(pelicula)
            }
        }
    }

    fun actualizarEncoladas(listaNueva: List<Pelicula>){
        peliculasEncoladas.clear()
        peliculasEncoladas.addAll(listaNueva)
        notifyDataSetChanged()
    }
}