package com.bryukhanov.onlyofficetesttask.other

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.databinding.FragmentRoomsBinding

class RoomsFragment : BaseFragment<FragmentRoomsBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentRoomsBinding {
        return FragmentRoomsBinding.inflate(inflater, container, false)
    }

}