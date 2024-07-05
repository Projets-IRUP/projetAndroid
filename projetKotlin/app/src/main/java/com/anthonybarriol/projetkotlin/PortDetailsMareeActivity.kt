package com.anthonybarriol.projetkotlin

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.Maree
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.ApiService
import service.RetrofitClient

class PortDetailsMareeActivity : AppCompatActivity() {
    private lateinit var textViewTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MareeAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_port_details_maree)

        textViewTitle = findViewById(R.id.textView_title)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MareeAdapter(emptyList())
        recyclerView.adapter = adapter

        val portNom = intent.getStringExtra("nom")
        val portId = intent.getIntExtra("port_id", 0)
        Log.d("PortDetailsMaree", "Port: $portNom, port_id: $portId")

        textViewTitle.text = portNom

        // API call
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        val requestBody = mapOf("id_port" to portId)
        val call = apiService.getMaree1j(requestBody)

        call.enqueue(object : Callback<List<Maree>> {
            override fun onResponse(call: Call<List<Maree>>, response: Response<List<Maree>>) {
                if (response.isSuccessful) {
                    val mareeList = response.body()
                    mareeList?.let { displayMarees(it) }
                } else {
                    Log.e("PortDetailsMaree", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Maree>>, t: Throwable) {
                Log.e("PortDetailsMaree", "Failure: ${t.message}")
            }
        })
    }

    private fun displayMarees(marees: List<Maree>) {
        adapter.updateMarees(marees)
    }
}
