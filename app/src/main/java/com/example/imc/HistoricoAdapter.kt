package com.example.imc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoricoAdapter(
    private val lista: MutableList<ImcRegistro>
) : RecyclerView.Adapter<HistoricoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtData: TextView = view.findViewById(R.id.txtData)
        val txtValores: TextView = view.findViewById(R.id.txtValores)
        val txtResultado: TextView = view.findViewById(R.id.txtResultado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_historico, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.txtData.text = "Data: ${item.data}"
        holder.txtValores.text = "Peso: ${item.peso} kg | Altura: ${item.altura} m"
        holder.txtResultado.text = "Resultado: ${item.resultado}"
    }

    override fun getItemCount(): Int = lista.size
}
