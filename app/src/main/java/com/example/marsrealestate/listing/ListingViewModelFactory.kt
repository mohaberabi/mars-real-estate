package com.example.marsrealestate.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListingViewModelFactory : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListingViewModel::class.java)) {
            return ListingViewModel() as T
        }

        throw IllegalArgumentException("CAN NOT INSTANTIOETE")
    }
}