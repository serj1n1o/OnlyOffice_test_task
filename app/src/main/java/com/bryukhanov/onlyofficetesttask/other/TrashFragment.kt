package com.bryukhanov.onlyofficetesttask.other

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.auth.ui.AuthFragment
import com.bryukhanov.onlyofficetesttask.databinding.FragmentTrashBinding


class TrashFragment : BaseFragment<FragmentTrashBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentTrashBinding {
        return FragmentTrashBinding.inflate(inflater, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }
}