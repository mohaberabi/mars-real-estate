package com.example.marsrealestate.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marsrealestate.network.MarsProperty

class DetailsViewModel(
    private val marsProperty: MarsProperty,
    private val app: Application
) :
    AndroidViewModel(app) {


    private val _selectedProperty = MutableLiveData<MarsProperty>()

    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty


    init {
        _selectedProperty.value = marsProperty

    }

}