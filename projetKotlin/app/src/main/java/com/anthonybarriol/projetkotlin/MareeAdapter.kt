package com.anthonybarriol.projetkotlin

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import model.Maree
import java.time.format.DateTimeFormatter

class MareeAdapter(private var marees: List<Maree>) : RecyclerView.Adapter<MareeAdapter.MareeViewHolder>() {

    class MareeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateHeureTextView: TextView = view.findViewById(R.id.dateHeureTextView)
        val hauteurTextView: TextView = view.findViewById(R.id.hauteurTextView)
        val typeTextView: TextView = view.findViewById(R.id.typeTextView)
        val coefficientTextView: TextView = view.findViewById(R.id.coefficientTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MareeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_maree, parent, false)
        return MareeViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MareeViewHolder, position: Int) {
        val maree = marees[position]
        val formatter = DateTimeFormatter.ofPattern("dd-mm HH:mm")
        holder.dateHeureTextView.text = maree.date_heure.format(formatter)
        holder.hauteurTextView.text = maree.hauteur.toString()
        if (maree.maree_type == 1){
            holder.typeTextView.text = "Haute"
        }else{
            holder.typeTextView.text = "Base"
        }

        holder.coefficientTextView.text = maree.coefficient.toString()
    }

    override fun getItemCount(): Int {
        return marees.size
    }

    fun updateMarees(newMarees: List<Maree>) {
        marees = newMarees
        notifyDataSetChanged()
    }
}
