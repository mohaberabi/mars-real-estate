package com.example.marsrealestate.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.marsrealestate.R
import com.example.marsrealestate.databinding.FragmentDetailsBinding
import com.example.marsrealestate.listing.ListingViewModel


class DetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentDetailsBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_details,
                container,
                false
            )
        val marsProperty = DetailsFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val applicatoin = requireNotNull(activity).application
        val factory = DetailsViewModelFactory(mars = marsProperty, app = applicatoin)
        val viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}