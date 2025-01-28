package com.bryukhanov.onlyofficetesttask.auth.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.databinding.FragmentAuthBinding

class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentAuthBinding {
        return FragmentAuthBinding.inflate(inflater, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }
}