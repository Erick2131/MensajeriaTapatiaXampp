package com.example.mensajeriatapatiaxampp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MensajeAdapter : RecyclerView.Adapter<MensajeAdapter.MensajeViewHolder>() {

    private var mensajes: List<Mensaje> = listOf()

    fun setItems(mensajes: List<Mensaje>) {
        this.mensajes = mensajes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mensaje, parent, false)
        return MensajeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MensajeViewHolder, position: Int) {
        val mensaje = mensajes[position]
        holder.bind(mensaje)
    }

    override fun getItemCount(): Int {
        return mensajes.size
    }

    class MensajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtContenido: TextView = itemView.findViewById(R.id.txtContenido)
        private val txtTipo: TextView = itemView.findViewById(R.id.txtTipo)
        private val txtFecha: TextView = itemView.findViewById(R.id.txtFecha)

        fun bind(mensaje: Mensaje) {
            txtContenido.text = "Contenido: ${mensaje.contenido}"
            txtTipo.text = "Tipo: ${mensaje.tipo}"
            txtFecha.text = "Fecha: ${mensaje.fecha}"
        }
    }
}
