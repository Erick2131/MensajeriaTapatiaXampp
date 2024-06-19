package com.example.mensajeriatapatiaxampp.ui.gallery

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mensajeriatapatiaxampp.ClassIP
import com.example.mensajeriatapatiaxampp.R
import com.example.mensajeriatapatiaxampp.databinding.FragmentGalleryBinding
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val CHANNEL_ID = "Canal de notificacion"
    private val notificationID = 100

    private lateinit var destinatario: Spinner
    private lateinit var direccion: EditText
    private lateinit var mensajero: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbMensaje: RadioButton
    private lateinit var rbPaquete: RadioButton
    private lateinit var contenido: EditText
    private lateinit var enviar: Button
    private var Cip: ClassIP = ClassIP()
    private var ip: String = Cip.ip

    private val destinatariosMap = mutableMapOf<String, Int>()
    private val mensajerosMap = mutableMapOf<String, Int>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val view = binding.root

        destinatario = view.findViewById(R.id.spnMenDestinatarios)
        direccion = view.findViewById(R.id.edtMenDireccion)
        mensajero = view.findViewById(R.id.spnMenMensajeros)
        radioGroup = view.findViewById(R.id.rgTipoM)
        rbMensaje = view.findViewById(R.id.rbMensaje)
        rbPaquete = view.findViewById(R.id.rbPaquete)
        contenido = view.findViewById(R.id.edtMenContenido)
        enviar = view.findViewById(R.id.btnMenEnviar)

        // Obtener destinatarios y mensajeros desde la base de datos
        obtenerDestinatarios()
        obtenerMensajeros()

        createNotificationChannel()

        // Acción del botón enviar
        enviar.setOnClickListener {
            // Obtener los valores seleccionados de los spinners y otros campos
            val nombreDestinatario = destinatario.selectedItem.toString()
            val nombreMensajero = mensajero.selectedItem.toString()

            val idDestinatario = destinatariosMap[nombreDestinatario] ?: 0
            val idMensajero = mensajerosMap[nombreMensajero] ?: 0
            val contenidoMensaje = contenido.text.toString()
            val tipoMensaje = if (rbMensaje.isChecked) "Mensaje" else "Paquete"
            val fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

            // Insertar el mensaje en la base de datos
            insertarMensaje(idDestinatario, idMensajero, contenidoMensaje, tipoMensaje, fechaActual)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun obtenerDestinatarios() {
        val url = "http://$ip/obtener_destinatarios.php"
        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                // Manejar la respuesta exitosa
                val listaDestinatarios = mutableListOf<String>()
                for (i in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(i)
                    val nombre = jsonObject.getString("nombre")
                    val id = jsonObject.getInt("idDestinatario") // Asegúrate de usar la clave correcta
                    listaDestinatarios.add(nombre)
                    destinatariosMap[nombre] = id
                }
                destinatario.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listaDestinatarios)
            },
            { error ->
                Toast.makeText(context, "Error al obtener los destinatarios: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(requireContext()).add(request)
    }

    fun obtenerMensajeros() {
        val url = "http://$ip/obtener_mensajeros.php"
        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                val listaMensajeros = mutableListOf<String>()
                for (i in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(i)
                    val empresa = jsonObject.getString("empresa")
                    val id = jsonObject.getInt("idMensajero") // Asegúrate de usar la clave correcta
                    listaMensajeros.add(empresa)
                    mensajerosMap[empresa] = id
                }
                mensajero.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listaMensajeros)
            },
            { error ->
                Toast.makeText(context, "Error al obtener los mensajeros: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(requireContext()).add(request)
    }


    fun insertarMensaje(idDestinatario: Int, idMensajero: Int, contenido: String, tipo: String, fecha: String) {
        val url = "http://$ip/insertar_mensaje.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                Toast.makeText(context, "Mensaje insertado correctamente", Toast.LENGTH_SHORT).show()
                createNotification("Mensajeria Tapatia", "Su mensaje se ha enviado correctamente")
            },
            { error ->
                error.printStackTrace()
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    val errorMsg = String(error.networkResponse.data)
                    Toast.makeText(context, "Error al insertar el mensaje: $errorMsg", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error al insertar el mensaje: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }) {

            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["idDestinatario"] = idDestinatario.toString()
                params["idMensajero"] = idMensajero.toString()
                params["contenido"] = contenido
                params["tipo"] = tipo
                params["fecha"] = fecha
                return params
            }
        }

        Volley.newRequestQueue(requireContext()).add(stringRequest)
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "Channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun createNotification(title: String, content: String) {
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is granted, show the notification
            notificationManager.notify(notificationID, builder.build())
        } else {
            // Permission is not granted, request the permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                notificationID
            )
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == notificationID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission was granted, create the notification
                createNotification("Permission Granted", "You can now receive notifications.")
            } else {
                // Permission was denied
                Toast.makeText(context, "Notification permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


