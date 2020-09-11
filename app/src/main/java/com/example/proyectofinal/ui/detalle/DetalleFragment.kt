package com.example.proyectofinal.ui.detalle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyectofinal.R
import com.example.proyectofinal.app.PeliculaApplication
import com.example.proyectofinal.ui.resenar.ResenaFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detalle.*

class DetalleFragment : Fragment() {

    private lateinit var detalleViewModel: DetalleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detalleViewModel =
            ViewModelProvider(this).get(DetalleViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_detalle, container, false)

        val args by navArgs<DetalleFragmentArgs>()
        val pelicula = args.pelicula

        if(detalleViewModel.peliculaDetallada.value == null) detalleViewModel._peliculaDetallada.postValue(pelicula)

        detalleViewModel.peliculaDetallada.observe(viewLifecycleOwner, Observer {
            val configuracion = PeliculaApplication.peliculaAPIConfiguration
            if(configuracion == null){
                Picasso.get().load(R.drawable.sin_conexion).into(posterDetalle)
            }else{
                val direccionCompleta = "${configuracion.images.secure_base_url}/${configuracion.images.poster_sizes.get(5)}/${it.poster_path}"
                Picasso.get().load(direccionCompleta).into(posterDetalle)
            }
            respuestaPorno.text =
                if(it.adult) "Sí"
                else "No"
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        encolar.setOnClickListener {
            val pelicula = detalleViewModel.peliculaDetallada.value
            detalleViewModel.encolarPelicula(pelicula!!)
            findNavController().navigate(DetalleFragmentDirections.irAEncoladas())
        }

        resenar.setOnClickListener {
            val pelicula = detalleViewModel.peliculaDetallada.value
            ResenaFragment.nuevaInstancia(pelicula!!).show(requireActivity().supportFragmentManager, "DETALLE")
        }
    }
}