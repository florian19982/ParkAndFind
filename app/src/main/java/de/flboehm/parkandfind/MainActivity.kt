package de.flboehm.parkandfind

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var locationManager : LocationManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create persistent LocationManager reference
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        // Get the widgets
        val parkButton = findViewById<Button>(R.id.park)
        val findButton = findViewById<Button>(R.id.find)
        val label = findViewById<TextView>(R.id.label)

        var longitude : Double
        var latitude : Double

        //define the listener
        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                label.setText("" + location.longitude + ":" + location.latitude)
                longitude = location.longitude
                latitude = location.latitude
            }
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        // Set a click listener for button widget
        parkButton.setOnClickListener{
            try {
                // Request location updates
                locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
                // TODO Write Location in File
            } catch(ex: SecurityException) {
                label.setText(R.string.error)
                Log.d("myTag", "Security Exception, no location available")
            }
        }

        findButton.setOnClickListener {
            // TODO Open Maps View with Loacation
        }



    }


}
