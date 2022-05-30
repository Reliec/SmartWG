package com.example.android.smartwg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.smartwg.repository.Repository
import com.example.myapplication.util.globals

class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val tvSharedAppartmentCode = findViewById<TextView>(R.id.edSharedAppartmentCode)
        tvSharedAppartmentCode.text = globals.userSACode.toString()

        val tvEmail = findViewById<TextView>(R.id.edUserEmail)
        tvEmail.text = globals.userEmail

        val tvPassword = findViewById<TextView>(R.id.edPassword)
        tvPassword.text = globals.userPassword

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val bConfirmChanges = findViewById<Button>(R.id.bConfirmChanges)

        bConfirmChanges.setOnClickListener {
            updateSettings(tvSharedAppartmentCode.text.toString().toInt(), tvEmail.text.toString(), tvPassword.text.toString())
        }
    }

    private fun updateSettings(newSACODE: Int?, newEMAIL: String?, newPASSWORD : String?){
        viewModel.updateSettingsViewM(globals.userSACode, newSACODE, globals.userEmail, newEMAIL, globals.userPassword, newPASSWORD)
        viewModel.echoStringResponse.observe(
            this,
            Observer{
                response->
                if(response.isSuccessful){
                    var resp: String= response.body().toString()
                    Toast.makeText(this,resp, Toast.LENGTH_LONG).show()
                    globals.userSACode = newSACODE
                    globals.userEmail = newEMAIL
                    globals.userPassword = newPASSWORD

                    System.out.println(globals.userSACode.toString() + globals.userEmail.toString() + globals.userPassword.toString())
                }
                else{
                    Toast.makeText(this,response.code().toString() + "Update Settings failed", Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}