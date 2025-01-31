package com.bryukhanov.onlyofficetesttask.auth.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.R
import com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel.AuthViewModel
import com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel.UserState
import com.bryukhanov.onlyofficetesttask.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel by viewModel<AuthViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userData()

        viewModel.getUserData().observe(viewLifecycleOwner) { userState ->
            when (userState) {
                is UserState.Content -> {
                    with(binding) {
                        userName.text = userState.user.userName
                        userEmail.text = userState.user.email
                        Glide.with(requireView())
                            .load(userState.user.avatar)
                            .fitCenter()
                            .placeholder(R.drawable.ic_photo_account)
                            .into(profilePhoto)
                    }
                }

                UserState.Empty -> {}
            }
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_profileFragment_to_authFragment)
        }
    }

}