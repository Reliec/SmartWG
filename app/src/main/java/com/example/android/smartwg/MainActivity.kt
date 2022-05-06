package com.example.android.smartwg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.smartwg.model.User
import com.example.android.smartwg.repository.Repository
import com.example.myapplication.util.globals
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val bSignUp = findViewById<Button>(R.id.bSignUp)
        bSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val bSignIn = findViewById<Button>(R.id.bSignIn)
        bSignIn.setOnClickListener {
            loginValidate()
        }
    }

    fun loginValidate(): Boolean {
        var etEmail = findViewById<EditText>(R.id.etEmail);
        var etPassword = findViewById<EditText>(R.id.etPasswordLogin);
        var loginValid: Boolean = false

        if (etEmail.text.toString() == "") {
            etEmail.error = "Leeres Feld Benutzername";
        }
        if (etPassword.text.toString() == "") {
            etPassword.error = "Leeres Feld Passwort"
        } else {
            loginValid = true
        }
        if (loginValid) {
            dbLogin(etEmail, etPassword)
        }
        return loginValid
    }

    fun dbLogin(etEmail: EditText, etPassword: EditText) {

        viewModel.getUserWherePasswordViewM(etEmail.text.toString(), etPassword.text.toString())
        viewModel.userWherePasswordResponse.observe(
            this,
            object : androidx.lifecycle.Observer<Response<List<User>>> {
                override fun onChanged(t: Response<List<User>>?) {
                    for (i in t?.body()!!) {
                        System.out.println(t?.body()?.get(0)?.EMAIL)
                        System.out.println(t?.body()?.get(0)?.PASSWORD)

                        if (t?.body()?.get(0)?.EMAIL.toString() == etEmail.text.toString() &&
                            t?.body()?.get(0)?.PASSWORD.toString() == etPassword.text.toString()
                        ) {
                            globals.userEmail = t?.body()?.get(0)?.EMAIL
                            globals.userPassword = t?.body()?.get(0)?.PASSWORD

                            Toast.makeText(
                                this@MainActivity,
                                "Login successful !",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            val intent = Intent(this@MainActivity, HomescreenActivity::class.java)
                            startActivity(intent)
                        }
                        Toast.makeText(
                            this@MainActivity,
                            "Benutzername existiert nicht oder das Passwort ist falsch !",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })
    }
}