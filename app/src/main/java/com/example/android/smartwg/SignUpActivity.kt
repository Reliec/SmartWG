package com.example.android.smartwg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.smartwg.repository.Repository

class SignUpActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var etName = findViewById<EditText>(R.id.etName);
        var etFirstName = findViewById<EditText>(R.id.etFirstName);
        var etSACode = findViewById<EditText>(R.id.etSACode);
        var etEmail = findViewById<EditText>(R.id.etEmail);
        var etEmail2 = findViewById<EditText>(R.id.etEmail2);
        var etPassword = findViewById<EditText>(R.id.etPasswordSignUp);
        var etPassword2 = findViewById<EditText>(R.id.etPassword2SignUp);

        var cboxPrivacyPolicy = findViewById<CheckBox>(R.id.cboxPrivacyPolicy);
        var cboxTermsOfService = findViewById<CheckBox>(R.id.cboxTermsOfService);

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val bRegister = findViewById<Button>(R.id.bRegister)
        bRegister.setOnClickListener{
            if(validateRegister(etName, etFirstName, etSACode, etEmail, etEmail2, etPassword, etPassword2) && validateCheckbox(cboxPrivacyPolicy, cboxTermsOfService)){
                signUpdb(etName, etFirstName, etSACode, etEmail, etPassword)
            }
            else if(validateRegister(etName, etFirstName, etSACode, etEmail, etEmail2, etPassword, etPassword2) == false){
                Toast.makeText(this@SignUpActivity, "One or more input boxes not filled in !", Toast.LENGTH_LONG).show()
            }
            else if(validateCheckbox(cboxPrivacyPolicy, cboxTermsOfService) == false){
                Toast.makeText(this@SignUpActivity, "Private Policy or Terms of Service not accepted !", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun validateRegister(etName: EditText, etFirstName: EditText, etSACode: EditText, etEmail: EditText, etEmail2:EditText, etPassword: EditText, etPassword2:EditText): Boolean{

        if(etName.text.toString() == ""){
            etName.error = "Empty field Name";
            return false
        }
        if(etFirstName.text.toString() == ""){
            etFirstName.error = "Empty field first name";
            return false
        }
        if(etSACode.text.toString() == ""){
            etSACode.error = "Empty field Shared Appartment Code";
            return false
        }
        if(etEmail.text.toString() == ""){
            etEmail.error = "Empty field Email";
            return false
        }
        if(etEmail2.text.toString() == ""){
            etEmail2.error = "Empty field confirm Email";
            return false
        }
        if(etPassword.text.toString() == ""){
            etPassword.error = "Empty field Password";
            return false
        }
        if(etPassword2.text.toString() == ""){
            etPassword2.error = "Empty Field confirm Password";
            return false
        }
        if(etEmail.text.toString() != etEmail2.text.toString()){
            etEmail.error = "The Emails mismatch !"
            etEmail2.error = "The Emails mismatch !"
            return false
        }
        if(etPassword.text.toString() != etPassword2.text.toString()){
            etPassword.error = "The Passwords mismatch !"
            etPassword2.error = "The Passwords mismatch !"
            return false
        }
        return true
    }

    fun validateCheckbox(cboxPrivacyPolicy: CheckBox, cboxTermsOfService: CheckBox): Boolean{

        if(cboxPrivacyPolicy.isChecked() == true && cboxTermsOfService.isChecked() == true){
            return true
        }
        return false
    }

    fun signUpdb(etName : EditText, etFirstName: EditText, etSACode: EditText, etEmail:EditText, etPassword: EditText){
        viewModel.createNewUserViewM(etFirstName.text.toString(), etName.text.toString(), etEmail.text.toString(), etPassword.text.toString(), etSACode.text.toString().toInt())
        viewModel.echoStringResponse.observe(this, Observer{
                response ->
            if(response.isSuccessful){
                var resp: String= response.body().toString()
                Toast.makeText(this,resp, Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,response.code().toString() + " Registrierung Fehlgeschlagen", Toast.LENGTH_LONG).show()
            }
        })
        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
    }

}