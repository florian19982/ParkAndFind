package de.flboehm.parkandfind

import android.content.Context
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.File
import java.io.IOException
import java.io.PrintWriter

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Get the widgets
        val parkButton = findViewById<Button>(R.id.park)
        val findButton = findViewById<Button>(R.id.find)
        val label = findViewById<TextView>(R.id.label)

        parkButton.setOnClickListener{
            label.text = getString(R.string.park)
            // TODO Get and store last known location
        }

        findButton.setOnClickListener {
            label.text = getString(R.string.find)
            // TODO Open Maps View with location
        }

    }

    fun storeLocation(location: Location) {
        val path = getDir("ParkAndFind", Context.MODE_PRIVATE)
        var success = true
        if (!path.exists()) {
            success = path.mkdir()
        }
        if (success) {
            val fileLong = File("longitude.txt")
            val fileLat = File("latitude.txt")
            if (!fileLong.exists()) {
                success = fileLong.mkdir()
            }
            if (success && !fileLat.exists()) {
                success = fileLat.mkdir()
            }
            if (success) {
                // directory exists or already created
                try {
                    PrintWriter(fileLong).use { out -> out.print(location.longitude) }
                    PrintWriter(fileLat).use { out -> out.print(location.latitude) }
                } catch (e: IOException) {
                    // handle the exception
                }

            } else {
                // directory creation is not successful
            }
        }
    }


}
