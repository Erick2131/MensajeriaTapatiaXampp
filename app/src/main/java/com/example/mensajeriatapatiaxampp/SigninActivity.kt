package com.example.mensajeriatapatiaxampp
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import org.json.JSONArray

class SigninActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var backButton: Button
    private lateinit var registerButton: Button
    private var Cip : ClassIP = ClassIP()
    private var ip: String = Cip.ip
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        nameEditText = findViewById(R.id.edtSignName)
        ageEditText = findViewById(R.id.edtSignAge)
        emailEditText = findViewById(R.id.edtSignEmail)
        passwordEditText = findViewById(R.id.edtSignPassword)
        backButton = findViewById(R.id.btnSignBack)
        registerButton = findViewById(R.id.btnSignRegister)

        // Agrega el evento OnClickListener al botón de registro
        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Verifica que los campos no estén vacíos
            if (name.isNotEmpty() && age.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Realiza la inserción en la base de datos
                insertUser(name, age.toInt(), email, password)
            } else {
                // Muestra un mensaje si algún campo está vacío
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método para insertar un usuario en la base de datos
    private fun insertUser(name: String, age: Int, email: String, password: String) {
        // URL del archivo PHP que maneja la inserción en la base de datos
        //val url = "http://192.168.137.76/insert_user.php"
        val url = "http://$ip/insert_user.php"
        // Crea una solicitud de tipo StringRequest
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Response.Listener { response ->
                // Muestra un mensaje de éxito o error según la respuesta del servidor
                if (response == "1") {
                    Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error en el registro.", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                // Muestra un mensaje en caso de error de conexión
                Toast.makeText(this, "Error en la conexión: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            // Sobreescribe el método getParams() para enviar los parámetros POST
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["name"] = name
                params["age"] = age.toString()
                params["email"] = email
                params["password"] = password
                params["isAdmin"] = "false" // Por defecto isAdmin es false
                return params
            }
        }

        // Añade la solicitud a la cola de solicitudes
        Volley.newRequestQueue(this).add(stringRequest)
    }
}

