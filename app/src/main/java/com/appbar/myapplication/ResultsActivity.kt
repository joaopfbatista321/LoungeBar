package com.appbar.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val textViewResults = findViewById<TextView>(R.id.textViewResults)
        val bundle = intent.getBundleExtra("qrBundle")

        // Aqui vamos construir a string baseada nos dados recebidos
        val emitente = bundle?.getString("A") ?: "Desconhecido"
        val adquirente = bundle?.getString("B") ?: "Desconhecido"
        val paisAdquirente = bundle?.getString("C") ?: "Desconhecido"
        val tipoDocumento = bundle?.getString("D") ?: "Desconhecido"
        val estadoDocumento = bundle?.getString("E") ?: "Desconhecido"
        val dataDocumento = bundle?.getString("F") ?: "Desconhecido"
        val identificacaoDocumento = bundle?.getString("G") ?: "Desconhecido"
        val atcud = bundle?.getString("H") ?: "Desconhecido"
        val impostos = bundle?.getString("N") ?: "0.00"
        val totalComImpostos = bundle?.getString("O") ?: "0.00"
        val hash = bundle?.getString("Q") ?: "N/A"
        val numCertificado = bundle?.getString("R") ?: "N/A"

        // Formatação da string para exibição
        val summary = """
            Emitente: $emitente
            Adquirente: $adquirente
            País do Adquirente: $paisAdquirente
            Tipo de Documento: $tipoDocumento
            Estado do Documento: $estadoDocumento
            Data do Documento: $dataDocumento
            Identificação do Documento: $identificacaoDocumento
            ATCUD: $atcud
            Total de Impostos: $impostos
            Total com Impostos: $totalComImpostos
            Hash: $hash
            Número do Certificado: $numCertificado
        """.trimIndent()

        textViewResults.text = summary
    }
}




