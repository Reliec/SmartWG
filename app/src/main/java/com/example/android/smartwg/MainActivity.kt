package com.example.android.smartwg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.smartwg.MainViewModelFactory
import com.example.android.smartwg.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        displayUserName()
    }

    fun displayUserName(){
        var tvHello = findViewById<TextView>(R.id.tvTest)
        viewModel.getUserDataViewM()
        viewModel.userListResponse.observe(this, Observer { response ->
            for(i in 0 until (response.body()?.count() ?: 0)) {
                System.out.println(response.body()?.get(i)?.toString())
                System.out.println(response.body()?.get(i)?.NAME)
                System.out.println(response.body()?.get(i)?.VORNAME)
                System.out.println(response.body()?.get(i)?.EMAIL)
                System.out.println(response.body()?.get(i)?.PASSWORD)
            }
            tvHello.text = response.body()?.get(1)?.NAME
        })
    }
}