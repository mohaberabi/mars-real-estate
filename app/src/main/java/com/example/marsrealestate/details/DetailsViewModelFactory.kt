package com.example.marsrealestate.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marsrealestate.network.MarsProperty

class DetailsViewModelFactory(
    private val mars: MarsProperty,
    private val app: Application
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(marsProperty = mars, app = app) as T

        }
        throw IllegalArgumentException("ERRRO CREATING VIEWMODEL NOT ALLOWED ")
    }
}