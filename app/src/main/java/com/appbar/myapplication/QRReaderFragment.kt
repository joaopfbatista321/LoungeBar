package com.appbar.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appbar.myapplication.databinding.FragmentQrReaderBinding
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class QRReaderFragment : Fragment() {
    private var _binding: FragmentQrReaderBinding? = null
    private val binding get() = _binding!!
    private lateinit var barcodeView: DecoratedBarcodeView

    // Propriedade para armazenar os dados do QR code
    private var qrData: Map<String, String> = mapOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQrReaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        barcodeView = binding.barcodeScanner
        barcodeView.decodeSingle(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                val qrString = result.text
                qrData = parseQRCodeData(qrString)  // Atualiza qrData com os dados mais recentes

                // Pode mostrar uma visualização rápida dos dados via Snackbar, por exemplo
                Snackbar.make(binding.root, "Dados recebidos, clique em 'Ver Resultados' para detalhes.", Snackbar.LENGTH_LONG).show()
            }

            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
        })

        binding.btnViewResults.setOnClickListener {
            val intent = Intent(activity, ResultsActivity::class.java)
            val bundle = Bundle()
            qrData.forEach { (key, value) ->
                bundle.putString(key, value)
            }
            intent.putExtra("qrBundle", bundle)
            startActivity(intent)
        }
    }

    // Função para parsear a string do QR code
    private fun parseQRCodeData(qrData: String): Map<String, String> {
        val fields = qrData.split('*')
        val dataMap = mutableMapOf<String, String>()
        fields.forEach { field ->
            val keyValue = field.split(':')
            if (keyValue.size == 2) {
                dataMap[keyValue[0].trim()] = keyValue[1].trim()
            }
        }
        return dataMap
    }



    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }


}

