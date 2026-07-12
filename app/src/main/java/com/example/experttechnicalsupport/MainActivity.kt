package com.example.experttechnicalsupport

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ScrollView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.net.URL


class MainActivity : AppCompatActivity() {

    //variables globales cawn
    val ip = "http://100.123.9.5"
    val proyecto = "/ExpertTechnicalSupport/backend_movil/login_movil.php?emailEmp="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //variables que requiere el oncreate cawn
        val queue = Volley.newRequestQueue(this)    //el ke wey
        val etCorreo = findViewById<EditText>(R.id.etCorreo)    // se llama correo para recordar en la bd
        val etPassword = findViewById<EditText>(R.id.etPassword)    // ps la contraseña cawn
        val btnLogin = findViewById<Button>(R.id.btnLogin)     // el que te gusta que te piquen


        /*
        //aqui va a ir un intento pedorro de que el card
        //se mueva para arriba para que se vea mas limpio cuando aparece el teclado
        val scvMain = findViewById<ScrollView>(R.id.scvMain) //variable del scrollview

        etCorreo.setOnFocusChangeListener { _, tieneFoco ->
            if (tieneFoco) {
                scvMain.postDelayed({
                    scvMain.smoothScrollTo(0, scvMain.bottom)
                }, 300)
            }
        }

        etPassword.setOnFocusChangeListener { _, tieneFoco ->
            if (tieneFoco) {
                scvMain.postDelayed({
                    scvMain.smoothScrollTo(0, scvMain.bottom)
                }, 300)
            }
        }


        NO MAMEN NO SIRVIO

        PUTA MADRE ES LA TERCERA VEZ QUE INTENTO QUE ESA MIERDA SE ACOMODE

        */


        //el listener del boton
        btnLogin.setOnClickListener {
            inicioSesion(ip + proyecto +
                    etCorreo.text.toString()
                , etPassword.text.toString()
                , queue)
        }
    } // cierre de oncreate

    fun inicioSesion(url: String,pass: String, queue: RequestQueue){

        // se crea la solcitud yeison
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                // Handle successful response (response is a JSONArray)
                try {
                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)

                        if(jsonObject.getString("password").equals(pass)){
                            Toast.makeText(this,"Bienvenido al sistema", Toast.LENGTH_LONG).show();
                            val intent = Intent(this, MisTickets::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,"Contraseña incorrecta, favor de verificar",Toast.LENGTH_LONG).show();
                        }//cierre del else

                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                // Handle network or parsing errors
                Log.e("VolleyError", "Error: ${error.message}")
            }
        )

        // 3. Add the request to the RequestQueue
        queue.add(jsonArrayRequest)

    }//inicioSesion

} // cierre de toodototote alv