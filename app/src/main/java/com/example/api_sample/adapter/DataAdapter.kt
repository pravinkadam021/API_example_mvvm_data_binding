package com.example.api_sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api_sample.R
import com.example.api_sample.databinding.CustomEmployeeTileBinding
import com.example.api_sample.models.Data
import com.example.api_sample.models.EmployeeData

class DataAdapter(private val context: Context, private val employeeList: ArrayList<Data>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_employee_tile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = employeeList[position]
        holder.binding.sample=data
    }

    override fun getItemCount() = employeeList.size

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val binding = CustomEmployeeTileBinding.bind(view)
    }

}