package com.androidproficiencyapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidproficiencyapp.data.api.ApiHelper
import com.androidproficiencyapp.utils.Resource
import androidx.lifecycle.viewModelScope
import com.androidproficiencyapp.data.model.Rows
import kotlinx.coroutines.launch

class MainViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    private val listOfRow = MutableLiveData<Resource<List<Rows>>>()
    private val title = MutableLiveData<String>()

    init {
        fetchListOfCanada()
    }

    private fun fetchListOfCanada() {
        viewModelScope.launch {
            listOfRow.postValue(Resource.loading(data = null))
            try {
                val rowsFromApi = apiHelper.getList()
                val appTitle = apiHelper.getTitle()

                listOfRow.postValue(Resource.success(data = rowsFromApi))
                title.postValue(appTitle)

            } catch (exception: Exception) {
                title.postValue("")
                listOfRow.postValue(Resource.error(data = null, message = exception.toString()))
            }
        }
    }

    fun getTitle(): LiveData<String> {
        return title
    }
    fun getList(): LiveData<Resource<List<Rows>>> {
        return listOfRow
    }

}