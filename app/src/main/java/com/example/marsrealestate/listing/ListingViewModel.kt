package com.example.marsrealestate.listing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsrealestate.network.MarsApi
import com.example.marsrealestate.network.MarsFilter
import com.example.marsrealestate.network.MarsProperty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

enum class MarsApiStatus { INITIAL, LOADING, ERROR, DONE }

class ListingViewModel : ViewModel() {

    init {
        getRealEstate(MarsFilter.SHOW_ALL)
    }

    private val _status = MutableLiveData<MarsApiStatus>().apply {
        value = MarsApiStatus.INITIAL
    }

    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>().apply { value = emptyList() }
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty?>()

    val navigateToSelectedProperty: LiveData<MarsProperty?>
        get() = _navigateToSelectedProperty

    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }


    private val _property = MutableLiveData<MarsProperty>()


    val property: LiveData<MarsProperty>
        get() = _property

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


    fun updateFilter(filter: MarsFilter) {
        getRealEstate(filter)
    }

    private fun getRealEstate(filter: MarsFilter) {

//                _status.value = MarsApiStatus.LOADING
        viewModelScope.launch {
            try {
                val res = MarsApi.retroFitServices.getProperties(filter.value)
                _properties.value = res
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {

                _status.value = MarsApiStatus.ERROR
                _properties.value = emptyList()
            }
        }

    }
}

