package com.example.labsny.ui


import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.labsny.R
import com.example.labsny.ui.bases.BaseActivity
import com.example.labsny.databinding.ActivityHomeBinding
import com.example.labsny.ui.home.HomeFragment
import java.io.IOException
import java.util.Locale

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val bindingInflate: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate
    private val latitude = 30.1559912
    private val longitude = 31.3353799

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }


    private fun setup() {
        toHome(latitude, longitude, getAddress())
    }

    private fun getAddress(): String {
        val result = StringBuilder()
        try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses!!.isNotEmpty()) {
                val address: Address = addresses[0]
                result.append(address.locality).append("\n")
                result.append(address.countryName)
            }
        } catch (e: IOException) {
            Toast.makeText(this@HomeActivity, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
        return result.toString()
    }

    private fun toHome(lat: Double, lon: Double, cityName: String) {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_home_container,
                HomeFragment.newInstance(lat.toString(), lon.toString(), cityName),
                HomeFragment::class.java.name
            )
            setReorderingAllowed(true)
        }
    }
}