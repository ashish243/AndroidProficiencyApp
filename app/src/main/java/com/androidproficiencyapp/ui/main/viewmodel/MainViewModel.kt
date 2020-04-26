package com.androidproficiencyapp.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.androidproficiencyapp.data.repository.MainRepository
import com.androidproficiencyapp.utils.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            Log.d("MainViewModel","Success")
            emit(Resource.success(data = mainRepository.getList()))
        } catch (exception: Exception) {
            Log.d("MainViewModel","Exception ${exception.message ?: "Error Occurred!"}")
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}