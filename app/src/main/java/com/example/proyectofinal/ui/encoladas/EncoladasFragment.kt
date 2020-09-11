package com.example.proyectofinal.ui.encoladas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.ui.adapters.EncoladasAdapter
import kotlinx.android.synthetic.main.fragment_encoladas.*

class EncoladasFragment : Fragment() {

    private lateinit var encoladasViewModel: EncoladasViewModel
    private var encoladasAdapter = EncoladasAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        encoladasViewModel =
            ViewModelProvider(this).get(EncoladasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_encoladas, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerEncoladas)

        encoladasViewModel.listaEncoladas.observe(viewLifecycleOwner, Observer {
            encoladasAdapter.actualizarEncoladas(it)
            recyclerView.adapter = encoladasAdapter
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        borrarEncoladasTodas.setOnClickListener { encoladasViewModel.borrarEncoladasTodas() }
    }
}