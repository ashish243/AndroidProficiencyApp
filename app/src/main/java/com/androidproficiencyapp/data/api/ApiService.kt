package com.androidproficiencyapp.data.api

import com.androidproficiencyapp.data.model.AboutCanada
import retrofit2.http.GET

interface ApiService {
    @GET("facts.json")
    suspend fun getList(): AboutCanada
}