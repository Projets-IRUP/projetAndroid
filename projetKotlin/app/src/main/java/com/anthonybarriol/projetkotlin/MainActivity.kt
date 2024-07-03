package com.anthonybarriol.projetkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import model.Port
import service.ApiService
import service.RetrofitClient

class MainActivity : AppCompatActivity() {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(applicationContext, getPreferences(MODE_PRIVATE))
        setContentView(R.layout.activity_main)

        // Initialize map
        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        mapView.setMultiTouchControls(true)
        val mapController = mapView.controller

        val defaultLatitude = 46.603354
        val defaultLongitude = 1.888334
        val zoomLevel = 7.0
        mapController.setZoom(zoomLevel)
        mapController.setCenter(GeoPoint(defaultLatitude, defaultLongitude))

        // API call
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        val call = apiService.getPort()

        call.enqueue(object : Callback<List<Port>> {
            override fun onResponse(call: Call<List<Port>>, response: Response<List<Port>>) {
                if (response.isSuccessful) {
                    val portList = response.body()
                    portList?.forEach { port ->
                        Log.d("MainActivity", "Port: $port")
                        addMarkerToMap(port)
                    }
                } else {
                    Log.e("MainActivity", "Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Port>>, t: Throwable) {
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }

    private fun addMarkerToMap(port: Port) {
        val drawable = ContextCompat.getDrawable(this, R.drawable.localisation)
        val startMarker = Marker(mapView)
        startMarker.position = GeoPoint(port.latitude, port.longitude)
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        startMarker.title = port.nom
        startMarker.icon = drawable
        startMarker.setOnMarkerClickListener { _, _ ->
            val intent = Intent(this, PortDetailsMareeActivity::class.java).apply {
                putExtra("nom", port.nom)
                putExtra("port_id", port.id_port)
            }
            startActivity(intent)
            true
        }
        mapView.overlays.add(startMarker)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}
