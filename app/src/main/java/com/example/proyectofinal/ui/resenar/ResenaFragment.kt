package com.example.proyectofinal.ui.resenar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.R
import com.example.proyectofinal.model.Pelicula
import com.example.proyectofinal.ui.detalle.DetalleFragmentDirections
import com.example.proyectofinal.ui.encoladas.EncoladasFragmentDirections
import kotlinx.android.synthetic.main.fragment_resena.*
import kotlinx.android.synthetic.main.fragment_resena.view.*

class ResenaFragment : DialogFragment() {

    private lateinit var resenaViewModel: ResenaViewModel

    companion object {
        fun nuevaInstancia(pelicula: Pelicula): ResenaFragment {
            val fragmento = ResenaFragment()
            val argumentos = Bundle()
            argumentos.putSerializable("PELICULA", pelicula)
            fragmento.arguments = argumentos
            return fragmento
        }
    }

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resenaViewModel =
            ViewModelProvider(this).get(ResenaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_resena, container, false)

        if(resenaViewModel.peliculaAResenar.value == null){
            val pelicula = requireArguments().getSerializable("PELICULA") as Pelicula
            resenaViewModel._peliculaAResenar.postValue(pelicula)
        }

        resenaViewModel.peliculaAResenar.observe(this, Observer {
            tituloResenada.text = it.original_title
            estrellasResenada.rating = it.estrellas?:0f
            resena.setText(it.resena)
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resenar.setOnClickListener {
            val pelicula = resenaViewModel.peliculaAResenar.value!!
            pelicula.estrellas = view.estrellasResenada.rating
            pelicula.resena = view.resena.text.toString()
            resenaViewModel.resenarPelicula(pelicula)
            try{
                findNavController().navigate(DetalleFragmentDirections.irAResenadasDesdeDetalle())
            }catch(exception: Exception){}
            try {
                findNavController().navigate(EncoladasFragmentDirections.irAResenadasDesdeEncoladas())
            }catch (exception: Exception){}
            dismiss()
        }

        cancelar.setOnClickListener { dismiss() }
    }
}