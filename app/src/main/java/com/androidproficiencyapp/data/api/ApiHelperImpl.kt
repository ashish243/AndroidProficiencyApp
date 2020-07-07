package com.androidproficiencyapp.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getTitle() = apiService.getList().title
    override suspend fun getList() = apiService.getList().rows

}