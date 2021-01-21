package com.example.api_sample.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.api_sample.models.EmployeeData
import com.example.api_sample.network.GetApiData
import com.example.api_sample.network.RetrofitClientInstance
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    fun getDataFromServer(): MutableLiveData<EmployeeData?> {
        return callGetDataFromServerApi()
    }

    private fun callGetDataFromServerApi(): MutableLiveData<EmployeeData?> {
        val mutableLiveData: MutableLiveData<EmployeeData?> = MutableLiveData()

        val service: GetApiData = RetrofitClientInstance.getRetrofitInstance().create(GetApiData::class.java)
        val url = "http://dummy.restapiexample.com/api/v1/employees"
        val call: Call<EmployeeData> = service.getData(url)
        call.enqueue(object : Callback<EmployeeData> {
            override fun onResponse(call: Call<EmployeeData>, response: Response<EmployeeData>) {
                Log.d("apiResponse", GsonBuilder().setPrettyPrinting().create().toJson(response))
                if (response.body() != null) {
                    mutableLiveData.setValue(response.body())
                } else {
                    mutableLiveData.setValue(null)
                }
            }

            override fun onFailure(call: Call<EmployeeData>, t: Throwable) {
                Log.d("apierror", "" + t.localizedMessage + " cause -->" + t.cause)
                mutableLiveData.setValue(null)
            }
        })

        return mutableLiveData
    }
}