package com.example.proyectofinal.ui.portada

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.ui.adapters.RecomendacionesAdapter

class RecomendacionesFragment : Fragment() {

    private lateinit var recomendacionesViewModel: RecomendacionesViewModel
    private var recomendacionesAdapter = RecomendacionesAdapter(mutableListOf())
    private lateinit var contexto: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contexto = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recomendacionesViewModel =
            ViewModelProvider(this).get(RecomendacionesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recomendaciones, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerRecomendaciones)

        recomendacionesViewModel.listaRecomendaciones.observe(viewLifecycleOwner, Observer {
            recomendacionesAdapter.actualizarRecomendaciones(it)
            recyclerView.adapter = recomendacionesAdapter
        })

        recomendacionesViewModel.noSeConecto.observe(viewLifecycleOwner, Observer {
            AlertDialog.Builder(contexto)
                .setMessage("No se pudo establecer conexión. \n\n$it")
                .setPositiveButton("Aceptar"){
                        dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }
                .create()
                .show()
        })

        recomendacionesViewModel.getRecomendaciones()

        return root
    }
}