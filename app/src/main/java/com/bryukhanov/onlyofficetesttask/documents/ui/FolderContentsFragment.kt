package com.bryukhanov.onlyofficetesttask.documents.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.auth.ui.AuthFragment
import com.bryukhanov.onlyofficetesttask.databinding.FragmentFolderContentsBinding

class FolderContentsFragment : BaseFragment<FragmentFolderContentsBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentFolderContentsBinding {
        return FragmentFolderContentsBinding.inflate(inflater, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }
}