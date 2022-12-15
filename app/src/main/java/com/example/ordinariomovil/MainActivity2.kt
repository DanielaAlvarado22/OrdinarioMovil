package com.example.ordinariomovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {

    lateinit var btnRegresar: Button
    lateinit var btnLogin: Button
    lateinit var btnRegistrar: Button
    lateinit var emailEditTxt: EditText
    lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnLogin = findViewById(R.id.btnLogin)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        emailEditTxt = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        btnRegresar = findViewById(R.id.btnRegresar)

        btnRegresar.setOnClickListener()
        {
            val lanzar = Intent(this, MainActivity::class.java)
            startActivity(lanzar)
        }

        //Hacer login
        setup()
    }

    private fun setup(){
        btnRegistrar.setOnClickListener {
            if (emailEditTxt.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    emailEditTxt.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val lanzar = Intent(this, MainActivity::class.java)
                        startActivity(lanzar)
                    } else {
                        showAlert()
                    }
                }
            }
        }

        btnLogin.setOnClickListener {
            if (emailEditTxt.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    emailEditTxt.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val lanzar = Intent(this, MainActivity::class.java)
                        startActivity(lanzar)
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("ACEPTAR",null)
        val dialog : AlertDialog = builder.create()
        dialog.show()
    }
}