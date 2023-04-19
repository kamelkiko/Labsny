package com.example.labsny.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.labsny.BuildConfig
import com.example.labsny.R
import com.example.labsny.data.ImageSource
import com.example.labsny.data.apisManager.weather.WeatherApi
import com.example.labsny.data.models.IdImage
import com.example.labsny.data.models.request.WeatherRequest
import com.example.labsny.data.models.response.WeatherResponse
import com.example.labsny.databinding.FragmentHomeBinding
import com.example.labsny.ui.bases.BaseFragment
import com.example.labsny.ui.home.presenter.HomeContract
import com.example.labsny.ui.home.presenter.HomePresenter
import com.example.labsny.util.Constants
import com.example.labsny.util.PrefsUtil
import com.example.labsny.util.WeatherCodes
import com.example.labsny.util.loadImage
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class HomeFragment : BaseFragment<FragmentHomeBinding, HomePresenter>(), HomeContract.IView {
    override val presenter: HomePresenter
        get() = HomePresenter(this, WeatherApi())

    private var weatherCode: Int? = null
    private var temperature: Double? = null

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private lateinit var cityName: String

    private val imageSource = ImageSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lat = arguments?.getString(Constants.LAT)
        val lon = arguments?.getString(Constants.LON)
        cityName = arguments?.getString(Constants.CITY_NAME) ?: "Egypt"
        setup("$lat,$lon")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBack()
    }

    private fun setup(location: String) {
        presenter.fetchData(WeatherRequest(location, BuildConfig.apikey))
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(weatherResponse: WeatherResponse.Data.Timeline.Interval.Values) {
        weatherCode = weatherResponse.weatherCode
        temperature = weatherResponse.temperature
        binding.textviewCityDate.text =
            SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
        binding.textviewCityName.text = cityName
        binding.textviewTempStatus.text =
            WeatherCodes.weatherRange[weatherCode].toString()
        binding.textviewTemperature.text = "$temperature°"
        binding.imgWeather.loadImage(weatherCondition(weatherCode!!))
        getClothes(weatherCode!!, temperature!!)
    }

    private fun addCallBack() {
        binding.btnSubmit.setOnClickListener {
            saveClothes()
        }
        binding.imgReload.setOnClickListener {
            getClothes(weatherCode!!, temperature!!)
        }

    }

    private fun saveClothes() {
        val currentDay = SimpleDateFormat("dd", Locale.getDefault()).format(Date())
        PrefsUtil.initPrefsUtil(requireContext()).day = currentDay
        Toast.makeText(requireContext(), "Done! have a nice day ♥", Toast.LENGTH_SHORT).show()
        binding.imgReload.visibility = View.INVISIBLE
    }

    override fun showData(weatherResponse: WeatherResponse.Data.Timeline.Interval.Values) {
        requireActivity().runOnUiThread {
            hideProgressBar()
            initViews(weatherResponse)
        }

    }

    private fun weatherCondition(code: Int) =
        when (code) {
            1001 -> R.drawable.sun_clouds
            in 0..1100 -> R.drawable.sun
            in 1101..2000 -> R.drawable.sun_clouds
            in 2100..4201 -> R.drawable.rain
            in 5000..5101 -> R.drawable.snow
            in 6000..7102 -> R.drawable.freeze
            else -> R.drawable.thunder
        }

    private fun initClothes(image: List<IdImage>) {
        binding.imgShirt.loadImage(image[Constants.SHIRT].imageSrc)
        binding.imgPants.loadImage(image[Constants.PANT].imageSrc)
        binding.imgShoes.loadImage(image[Constants.SHOES].imageSrc)

    }

    private fun getClothes(currentCode: Int, temperature: Double) {
        if (currentCode >= 4000) initClothes(imageSource.rainClothe.random() as List<IdImage>)
        else {
            when (temperature) {
                in Int.MIN_VALUE.toDouble()..30.0 -> {
                    initClothes(imageSource.winterClothe.random() as List<IdImage>)
                }

                in 31.0..Int.MAX_VALUE.toDouble() -> {
                    initClothes(imageSource.sunnyClothes.random() as List<IdImage>)
                }
            }
        }

    }

    override fun showError(error: IOException) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_SHORT).show()
            hideProgressBar()
        }

    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    companion object {
        fun newInstance(lat: String, lon: String, cityName: String) = HomeFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.LAT, lat)
                putString(Constants.LON, lon)
                putString(Constants.CITY_NAME, cityName)
            }
        }
    }
}