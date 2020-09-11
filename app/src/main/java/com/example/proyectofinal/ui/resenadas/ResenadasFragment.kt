package com.example.proyectofinal.ui.resenadas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.ui.adapters.ResenadasAdapter

class ResenadasFragment : Fragment() {

    private lateinit var resenadasViewModel: ResenadasViewModel
    private var resenadasAdapter = ResenadasAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resenadasViewModel =
            ViewModelProvider(this).get(ResenadasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_resenadas, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerResenadas)

        resenadasViewModel.listaResenadas.observe(viewLifecycleOwner, Observer {
            resenadasAdapter.actualizarResenadas(it)
            recyclerView.adapter = resenadasAdapter
        })
        return root
    }
}