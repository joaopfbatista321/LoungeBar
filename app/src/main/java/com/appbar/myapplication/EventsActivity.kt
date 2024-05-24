package com.appbar.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity

class EventsActivity : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: EventsExpandableListAdapter
    private lateinit var titleList: List<String>
    private lateinit var itemList: HashMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        // Configura o botão de voltar
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()  // Finaliza a atividade atual e volta para a anterior
        }

        // Configura o ExpandableListView
        expandableListView = findViewById(R.id.expandableListView)
        initData()
        adapter = EventsExpandableListAdapter(this, titleList, itemList)
        expandableListView.setAdapter(adapter)
    }

    private fun initData() {
        titleList = listOf("Música ao Vivo", "Karaokê", "DJ", "Comédia")
        itemList = HashMap()

        val musicaAoVivo = listOf(
            "Dia: 01/06, Hora: 20:00, Artista: Banda XYZ",
            "Dia: 05/06, Hora: 21:00, Artista: João Silva",
            "Dia: 12/06, Hora: 22:00, Artista: Maria & Banda"
        )

        val karaoke = listOf(
            "Dia: 02/06, Hora: 20:00, Artista: Diversos",
            "Dia: 09/06, Hora: 20:00, Artista: Diversos",
            "Dia: 16/06, Hora: 20:00, Artista: Diversos"
        )

        val dj = listOf(
            "Dia: 03/06, Hora: 22:00, Artista: DJ K1",
            "Dia: 10/06, Hora: 23:00, Artista: DJ M2",
            "Dia: 17/06, Hora: 22:30, Artista: DJ Z3"
        )

        val comedia = listOf(
            "Dia: 04/06, Hora: 21:00, Artista: Comediante A",
            "Dia: 11/06, Hora: 21:00, Artista: Comediante B",
            "Dia: 18/06, Hora: 21:00, Artista: Comediante C"
        )

        itemList[titleList[0]] = musicaAoVivo
        itemList[titleList[1]] = karaoke
        itemList[titleList[2]] = dj
        itemList[titleList[3]] = comedia
    }
}
