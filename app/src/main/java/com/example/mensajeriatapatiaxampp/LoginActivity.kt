package com.example.mensajeriatapatiaxampp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var exitButton: Button
    private var Cip : ClassIP = ClassIP()
    private var ip: String = Cip.ip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa los elementos de la interfaz
        usernameEditText = findViewById(R.id.edtUsername)
        passwordEditText = findViewById(R.id.edtPassword)
        loginButton = findViewById(R.id.btnLoginEntry)
        exitButton = findViewById(R.id.btnLoginExit)

        // Configura el OnClickListener para el botón de inicio de sesión
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Realiza la solicitud HTTP al servidor PHP para iniciar sesión
            //val url = "http://192.168.1.135/login.php"
            val url = "http://$ip/login.php"
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> { response ->
                    if (response == "success") {
                        // Inicio de sesión exitoso, redirige a la siguiente actividad
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Inicio de sesión fallido, muestra un mensaje de error
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    // Error en la solicitud HTTP, muestra un mensaje de error
                    Toast.makeText(this, "Error en la conexión", Toast.LENGTH_SHORT).show()
                }) {
                override fun getParams(): Map<String, String> {
                    // Parámetros para la solicitud POST
                    val params = HashMap<String, String>()
                    params["correo"] = username
                    params["contrasena"] = password
                    return params
                }
            }

            // Agrega la solicitud a la cola de solicitudes
            Volley.newRequestQueue(this).add(stringRequest)
        }

        // Configura el OnClickListener para el botón de salida
        exitButton.setOnClickListener {
            finish()
        }
    }

    // Función para redirigir a la actividad de registro de usuario
    fun gotoSignin(view: View) {
        val intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
    }
}
