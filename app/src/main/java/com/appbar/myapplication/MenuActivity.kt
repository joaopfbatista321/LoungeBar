package com.appbar.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: CustomExpandableListAdapter
    private lateinit var titleList: List<String>
    private lateinit var itemList: HashMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Configura o botão de voltar
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()  // Finaliza a atividade atual e volta para a anterior
        }

        // Configura o ExpandableListView
        expandableListView = findViewById(R.id.expandableListView)
        initData()
        adapter = CustomExpandableListAdapter(this, titleList, itemList)
        expandableListView.setAdapter(adapter)
    }

    private fun initData() {
        titleList = listOf("Coquetéis", "Whiskies", "Cervejas", "Snacks")
        itemList = HashMap()

        val coqueteis = listOf(
            "Margarita - €8", "Mojito - €7", "Caipirinha - €6", "Daiquiri - €7",
            "Piña Colada - €8", "Cosmopolitan - €9", "Mai Tai - €10", "Sex on the Beach - €9",
            "Long Island Iced Tea - €12", "Blue Lagoon - €8", "Tequila Sunrise - €7",
            "Bloody Mary - €8", "Gin Fizz - €9", "Pisco Sour - €10", "Singapore Sling - €11",
            "Tom Collins - €9", "White Russian - €9", "Black Russian - €8", "Whiskey Sour - €10",
            "Cuba Libre - €7"
        )

        val whiskies = listOf(
            "Jack Daniel's - €10", "Jameson - €9", "Chivas Regal - €12", "Glenfiddich - €14",
            "Johnnie Walker Black Label - €13", "Macallan - €18", "Glenlivet - €15", "Laphroaig - €17",
            "Talisker - €16", "Bushmills - €8", "Glenmorangie - €14", "Ardbeg - €18",
            "Balvenie - €16", "Lagavulin - €20", "Highland Park - €15", "Yamazaki - €22",
            "Hibiki - €25", "Maker's Mark - €12", "Knob Creek - €13", "Woodford Reserve - €14"
        )

        val cervejas = listOf(
            "Heineken - €5", "Budweiser - €5", "Guinness - €6", "Stella Artois - €5",
            "Corona - €6", "Beck's - €5", "Carlsberg - €5", "Amstel - €5", "Peroni - €6",
            "Bud Light - €4", "Coors Light - €4", "Miller Lite - €4", "Pilsner Urquell - €6",
            "Hoegaarden - €6", "Leffe - €6", "Duvel - €7", "Erdinger - €6", "Paulaner - €6",
            "Sam Adams - €6", "Blue Moon - €6"
        )

        val snacks = listOf(
            "Amendoins - €3", "Batatas Fritas - €4", "Nachos - €5", "Azeitonas - €3", "Queijo - €5",
            "Salsichas - €4", "Mini Hambúrgueres - €6", "Anéis de Cebola - €4", "Falafel - €5",
            "Pão de Alho - €3", "Almôndegas - €6", "Camarão Frito - €8", "Frango Frito - €7",
            "Tiras de Mozzarella - €5", "Nuggets de Frango - €5", "Bruschetta - €6",
            "Queijo Coalho - €4", "Empanadas - €5", "Salgadinhos - €4", "Tacos - €6"
        )

        itemList[titleList[0]] = coqueteis
        itemList[titleList[1]] = whiskies
        itemList[titleList[2]] = cervejas
        itemList[titleList[3]] = snacks
    }

}
