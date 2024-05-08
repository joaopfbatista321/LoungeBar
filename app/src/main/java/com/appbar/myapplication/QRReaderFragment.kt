package com.appbar.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.appbar.myapplication.databinding.FragmentQrReaderBinding
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.ResultPoint

class QRReaderFragment : Fragment() {

    private var _binding: FragmentQrReaderBinding? = null
    private val binding get() = _binding!!
    private lateinit var barcodeView: DecoratedBarcodeView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQrReaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        barcodeView = binding.barcodeScanner
        barcodeView.decodeSingle(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                // Aqui você pode lidar com o resultado
                // Por exemplo, mostrando um Snackbar com a mensagem do QR Code
                Snackbar.make(binding.root, "QR Code Detected: ${result.text}", Snackbar.LENGTH_LONG).show()
            }

            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
        })
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

