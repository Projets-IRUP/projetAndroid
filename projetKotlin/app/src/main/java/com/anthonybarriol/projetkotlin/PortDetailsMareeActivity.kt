package com.anthonybarriol.projetkotlin

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PortDetailsMareeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val port_nom = intent.getStringExtra("nom" )
        val port_id = intent.getIntExtra("port_id", 0)
        Log.d("PortDetailsMaree", "Port:$port_nom"+"port_id:$port_id" )





    }
}
