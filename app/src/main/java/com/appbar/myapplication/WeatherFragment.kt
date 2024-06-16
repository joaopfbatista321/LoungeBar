package com.appbar.myapplication



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.appbar.myapplication.network.WeatherResponse
import com.appbar.myapplication.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherFragment : Fragment() {

    private val BASE_URL = "https://api.weatherapi.com/v1/"
    private val API_KEY = "b7f02f681c464515a30141813241606" // Substitua pela sua chave de API
    private lateinit var cityTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var conditionTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var windTextView: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        cityTextView = view.findViewById(R.id.cityTextView)
        temperatureTextView = view.findViewById(R.id.temperatureTextView)
        conditionTextView = view.findViewById(R.id.conditionTextView)
        humidityTextView = view.findViewById(R.id.humidityTextView)
        windTextView = view.findViewById(R.id.windTextView)
        progressBar = view.findViewById(R.id.progressBar)

        fetchWeatherData("Tomar")
        return view
    }

    private fun fetchWeatherData(city: String) {
        progressBar.visibility = View.VISIBLE

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        service.getCurrentWeather(API_KEY, city).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    if (weatherResponse != null) {
                        cityTextView.text = city
                        temperatureTextView.text = "${weatherResponse.current.temp_c}°C"
                        conditionTextView.text = weatherResponse.current.condition.text
                        humidityTextView.text = "Humidity: ${weatherResponse.current.humidity}%"
                        windTextView.text = "Wind: ${weatherResponse.current.wind_kph} kph"
                    } else {
                        showError("Resposta nula da API")
                    }
                } else {
                    showError("Resposta não bem sucedida: Código ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                showError("Falha na chamada da API: ${t.message}")
            }
        })
    }

    private fun showError(message: String) {
        temperatureTextView.text = "Erro ao carregar dados"
        Log.e("WeatherFragment", message)
    }
}
