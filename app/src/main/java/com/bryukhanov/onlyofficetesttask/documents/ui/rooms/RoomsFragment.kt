package com.bryukhanov.onlyofficetesttask.documents.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.R
import com.bryukhanov.onlyofficetesttask.databinding.FragmentRoomsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomsFragment : BaseFragment<FragmentRoomsBinding>() {

    private val viewModel by viewModel<RoomsViewModel>()

    private val adapter by lazy { RoomsAdapter() }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentRoomsBinding {
        return FragmentRoomsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRooms()

        binding.rvRooms.adapter = adapter

        viewModel.getStateRooms().observe(viewLifecycleOwner) { state ->

            when (state) {
                is RoomsState.Content -> {
                    adapter.setData(state.rooms)
                }

                RoomsState.Empty -> {
                    adapter.clearData()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.empty_data),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                RoomsState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_user_data),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.clearData()
    }

}