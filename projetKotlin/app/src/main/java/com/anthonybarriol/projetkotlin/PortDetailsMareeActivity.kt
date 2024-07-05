package com.anthonybarriol.projetkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import model.Maree
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.ApiService
import service.RetrofitClient

class PortDetailsMareeActivity : AppCompatActivity() {
    private lateinit var textViewTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_port_details_maree)
        textViewTitle= findViewById<TextView>(R.id.textView_title)

        val port_nom = intent.getStringExtra("nom" )
        val port_id = intent.getIntExtra("port_id", 0)
        Log.d("PortDetailsMaree", "Port:$port_nom"+"port_id:$port_id" )

        textViewTitle.text = port_nom

        // API call
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        val requestBody = mapOf("id_port" to port_id)
        val call = apiService.getMaree1j(requestBody)

        call.enqueue(object : Callback<List<Maree>> {
            override fun onResponse(call: Call<List<Maree>>, response: Response<List<Maree>>) {
                if (response.isSuccessful) {
                    val mareeList = response.body()
                    mareeList?.forEach { maree ->
                        Log.d("PortDetailsMaree", "Maree: $maree")

                    }
                } else {
                    Log.e("PortDetailsMaree", "Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Maree>>, t: Throwable) {
                Log.e("PortDetailsMaree", "Failure: ${t.message}")
            }
        })
    }
}