package com.example.marsrealestate.listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marsrealestate.R
import com.example.marsrealestate.databinding.FragmentListingBinding
import com.example.marsrealestate.network.MarsFilter


class ListingFragment : Fragment() {
    private val viewModel: ListingViewModel by lazy {
        ViewModelProvider(this)[ListingViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding: FragmentListingBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_listing,
                container,

                false
            )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recView.adapter = ListingAdapter(ListingAdapter.ItemClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner) {
            if (null != it) {
                findNavController().navigate(
                    ListingFragmentDirections.actionListingFragmentToDetailsFragment(it)
                )
                viewModel.displayPropertyDetailsComplete()
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_rent_menu -> MarsFilter.SHOW_RENT
                R.id.show_buy_menu -> MarsFilter.SHOW_BUY
                else -> MarsFilter.SHOW_ALL

            }
        )
        return true
    }
}