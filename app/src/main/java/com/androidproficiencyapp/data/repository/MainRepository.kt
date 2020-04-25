package com.androidproficiencyapp.data.repository

import com.androidproficiencyapp.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getList() = apiHelper.getList()
}