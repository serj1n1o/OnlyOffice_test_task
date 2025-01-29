package com.bryukhanov.onlyofficetesttask.auth.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.R
import com.bryukhanov.onlyofficetesttask.databinding.FragmentAuthBinding

class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    private var portalAddress: String? = null
    private var email: String? = null
    private var password: String? = null

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentAuthBinding {
        return FragmentAuthBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextPortal.doOnTextChanged { text, _, _, _ ->
            portalAddress = if (!text.isNullOrEmpty()) {
                text.toString()
            } else {
                null
            }
        }

        binding.editTextEmail.doOnTextChanged { text, _, _, _ ->
            email = if (!text.isNullOrEmpty()) {
                text.toString()
            } else {
                null
            }
        }

        binding.editTextPassword.doOnTextChanged { text, _, _, _ ->
            password = if (!text.isNullOrEmpty()) {
                text.toString()
            } else {
                null
            }
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_documentsFragment)
            binding.progressBar.isVisible = true
        }
    }

}