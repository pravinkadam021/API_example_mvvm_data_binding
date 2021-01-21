package com.example.api_sample.network

import com.example.api_sample.models.EmployeeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetApiData {
    @GET
    fun getData(@Url url: String?): Call<EmployeeData>
}