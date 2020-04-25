package com.androidproficiencyapp.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getList() = apiService.getList()
}