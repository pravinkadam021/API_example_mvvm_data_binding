package com.example.api_sample

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.api_sample.adapter.DataAdapter
import com.example.api_sample.databinding.ActivityMainBinding
import com.example.api_sample.models.EmployeeData
import com.example.api_sample.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModelMain: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        getDataFromServer()
        setContentView(binding.root)
    }

    private fun getDataFromServer() {
        binding.progress.visibility = VISIBLE
        viewModelMain = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModelMain.getDataFromServer().observe(this, Observer<EmployeeData?> {
            if (it !== null) {
                getResponse(it)
            } else {
                Toast.makeText(this, "Something went wrong !", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getResponse(data: EmployeeData) {
        binding.progress.visibility = GONE
        binding.rvData.adapter = DataAdapter(this, data.data)
    }
}
