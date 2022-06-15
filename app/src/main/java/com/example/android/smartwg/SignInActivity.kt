package com.example.android.smartwg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.smartwg.repository.Repository
import com.example.myapplication.util.globals
import retrofit2.Response
import java.net.SocketTimeoutException

class SignInActivity : AppCompatActivity() {

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

        val etPassword = findViewById<EditText>(R.id.etPasswordLogin)
        etPassword.setOnEditorActionListener{v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE)
            {
                loginValidate()
                true
            }
            else{
                false
            }
        }

        val bSignIn = findViewById<Button>(R.id.bSignIn)
        bSignIn.setOnClickListener {
            loginValidate()
        }
    }

    /**
     * Validates the values the user typed in to sign in. Returns a boolean if the values are
     * in fact valid.
     *
     * @return
     */
    fun loginValidate(): Boolean {
        var etEmail = findViewById<EditText>(R.id.etEmail);
        var etPassword = findViewById<EditText>(R.id.etPasswordLogin);
        var loginValid: Boolean = false

        if (etEmail.text.toString() == "") {
            etEmail.error = "Empty field username";
        }
        if (etPassword.text.toString() == "") {
            etPassword.error = "Empty field password"
        } else {
            loginValid = true
        }
        if (loginValid) {
            dbLogin(etEmail, etPassword)
        }
        return loginValid
    }

    /**
     * Function that searches a unique email and password to see if the user exists. If so the user
     * is logged in. (Another Activity starts)
     * Global variables are also set here since there is a lot of information of the user that we
     * can get here simultaneously. Globals will come into use for future features.
     *
     * @param etEmail
     * @param etPassword
     */
    fun dbLogin(etEmail: EditText, etPassword: EditText) {
        viewModel.getUserWherePasswordViewM(etEmail.text.toString(), etPassword.text.toString())
        viewModel.userWherePasswordResponse.observe(
            this,
            Observer { response ->
                for (i in response?.body()!!) {
                    System.out.println(response?.body()?.get(0)?.EMAIL)
                    System.out.println(response?.body()?.get(0)?.PASSWORD)

                    if (response?.body()?.get(0)?.EMAIL.toString() == etEmail.text.toString() &&
                        response?.body()
                            ?.get(0)?.PASSWORD.toString() == etPassword.text.toString()
                    ) {
                        globals.userEmail = response?.body()?.get(0)?.EMAIL
                        globals.userPassword = response?.body()?.get(0)?.PASSWORD
                        globals.userId = response?.body()?.get(0)?.ID
                        globals.userFirstName = response?.body()?.get(0)?.FIRST_NAME
                        globals.userLastName = response?.body()?.get(0)?.NAME
                        globals.userSACode = response?.body()?.get(0)?.SACODE
                        globals.userWGBs = response?.body()?.get(0)?.WGGBS
                        globals.userWGName = response?.body()?.get(0)?.WGNAME

                        System.out.println(response?.body())

                        Toast.makeText(
                            this@SignInActivity,
                            "Login successful !",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(this@SignInActivity, HomescreenActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@SignInActivity,
                            "Username does not exist or password is wrong",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })
    }
}