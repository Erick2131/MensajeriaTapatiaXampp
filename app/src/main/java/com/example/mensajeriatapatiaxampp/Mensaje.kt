package com.example.mensajeriatapatiaxampp

data class Mensaje(
    val idMensaje: Int,
    val emisor: String,
    val destinatario: Int,
    val mensajero: Int,
    val tipo: String,
    val contenido: String,
    val fecha: String
)

