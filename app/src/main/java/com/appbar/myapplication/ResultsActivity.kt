package com.appbar.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val backButton = findViewById<Button>(R.id.btnBack)

        // Configura o ouvinte de clique para o botão Voltar
        backButton.setOnClickListener {
            finish()  // Fecha a atividade atual
        }
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
        val summary = getString(
            R.string.emitente_adquirente_pa_s_do_adquirente_tipo_de_documento_estado_do_documento_data_do_documento_identifica_o_do_documento_atcud_total_de_impostos_total_com_impostos_hash_n_mero_do_certificado,
            emitente,
            adquirente,
            paisAdquirente,
            tipoDocumento,
            estadoDocumento,
            dataDocumento,
            identificacaoDocumento,
            atcud,
            impostos,
            totalComImpostos,
            hash,
            numCertificado
        ).trimIndent()

        textViewResults.text = summary
    }

}




