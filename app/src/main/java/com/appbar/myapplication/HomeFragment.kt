package com.appbar.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Carrega (infla) o layout XML para este fragmento e o converte em uma hierarquia de objetos View.
        // O arquivo de layout fragment_home.xml é transformado em um objeto View e retornado.
        // O ViewGroup container é o pai ao qual a hierarquia de Views será anexada.
        // O parâmetro false indica que a View não deve ser anexada ao container imediatamente.
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura o botão de eventos para navegar para o EventsFragment
        val eventsButton = view.findViewById<Button>(R.id.eventsButton)
        eventsButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_events)
        }
    }
}
