package com.example.android.smartwg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.smartwg.repository.Repository
import com.example.myapplication.util.globals
import org.w3c.dom.Text

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

        getWGBS(globals.userSACode)

        val tvWGBs = findViewById<TextView>(R.id.edWGBS)
        tvWGBs.text = globals.userWGBs

        val tvWGName = findViewById<TextView>(R.id.edWGTitle)
        tvWGName.text = globals.userWGName

        tvWGBs.setOnEditorActionListener{v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                updateSettings(tvSharedAppartmentCode.text.toString().toInt(), tvEmail.text.toString(), tvPassword.text.toString(), tvWGBs.text.toString(),tvWGName.text.toString())
                true
            }
            else
                false
        }

        val bConfirmChanges = findViewById<Button>(R.id.bConfirmChanges)

        bConfirmChanges.setOnClickListener {
            updateSettings(tvSharedAppartmentCode.text.toString().toInt(), tvEmail.text.toString(), tvPassword.text.toString(), tvWGBs.text.toString(),tvWGName.text.toString())
        }
    }

    /**
     * Sends new Information to the backend and changes the old values to the new ones. Two Endpoints
     * are being called in one function because a MYSQL UPDATE statement cant alter 2 tables at once.
     *
     * @param newSACODE
     * @param newEMAIL
     * @param newPASSWORD
     * @param newWGBs
     */
    fun updateSettings(newSACODE: Int?, newEMAIL: String?, newPASSWORD : String?, newWGBs:String?, newTitle:String?){
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

                    System.out.println(globals.userSACode.toString() + globals.userEmail.toString() + globals.userPassword.toString() + globals.userWGBs.toString())
                }
                else{
                    Toast.makeText(this,response.code().toString() + "Update Settings failed", Toast.LENGTH_LONG).show()
                }
            }
        )
        Thread.sleep(1000)
        viewModel.updateWGBSViewM(globals.userWGBs, newWGBs, globals.userWGName,newTitle,globals.userSACode)
        viewModel.echoStringResponse.observe(
            this, Observer {
                response ->
                if(response.isSuccessful){
                    var resp: String = response.body().toString()
                    Toast.makeText(this, resp, Toast.LENGTH_LONG).show()
                    globals.userWGBs = newWGBs
                    globals.userWGName = newTitle
                }
                else{
                    Toast.makeText(this, response.code().toString() + "Update WGBS failed", Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    private fun getWGBS(SACODE:Int?){
        viewModel.getWGBSViewM(SACODE)
        viewModel.wgbStringResponse.observe(this, Observer{ response ->
            globals.userWGBs = response.body()?.get(0)?.WGGBS
            globals.userWGName = response.body()?.get(0)?.WGNAME
        })
    }
}