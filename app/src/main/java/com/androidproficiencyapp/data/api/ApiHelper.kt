package com.androidproficiencyapp.data.api

import com.androidproficiencyapp.data.model.Rows

interface ApiHelper {
    suspend fun getTitle(): String
    suspend fun getList(): List<Rows>

}