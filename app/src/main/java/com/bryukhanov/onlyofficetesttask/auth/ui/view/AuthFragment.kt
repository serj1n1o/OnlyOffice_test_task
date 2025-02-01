package com.bryukhanov.onlyofficetesttask.auth.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.R
import com.bryukhanov.onlyofficetesttask.auth.domain.model.StatusCode
import com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel.AuthState
import com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel.AuthViewModel
import com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel.LoginDataState
import com.bryukhanov.onlyofficetesttask.databinding.FragmentAuthBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    private var portalAddress: String? = null
    private var email: String? = null
    private var password: String? = null

    private val viewModel by viewModel<AuthViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentAuthBinding {
        return FragmentAuthBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.clearAuthData()

        viewModel.getLoginData().observe(viewLifecycleOwner) { state ->
            portalAddress = state.portalAddress
            email = state.email
            password = state.password
        }

        viewModel.getAuthState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is AuthState.ErrorAuth -> showError(state.errorCode)
                AuthState.AuthSuccess -> openDocuments()
                AuthState.Default -> {}
            }
        }

        binding.editTextPortal.doOnTextChanged { text, _, _, _ ->
            if (binding.inputLayoutPortal?.error != null && !text.isNullOrEmpty()) {
                binding.inputLayoutPortal?.error = null
            }
            portalAddress = if (!text.isNullOrEmpty()) {
                text.toString()
            } else {
                null
            }
            setLoginData()
            binding.btnLogin.isEnabled = checkData()
        }

        binding.editTextEmail.doOnTextChanged { text, _, _, _ ->
            if (binding.inputLayoutEmail?.error != null && !text.isNullOrEmpty()) {
                binding.inputLayoutEmail?.error = null
            }
            email = if (!text.isNullOrEmpty()) {
                text.toString()
            } else {
                null
            }
            setLoginData()
            binding.btnLogin.isEnabled = checkData()
        }

        binding.editTextPassword.doOnTextChanged { text, _, _, _ ->
            password = if (!text.isNullOrEmpty()) {
                text.toString()
            } else {
                null
            }
            setLoginData()
            binding.btnLogin.isEnabled = checkData()
        }

        binding.btnLogin.setOnClickListener {
            if (isValidPortalAddress(portalAddress) && isValidEmail(email)) {
                binding.progressBar.isVisible = true
                viewModel.authenticate()
            }
            if (!isValidPortalAddress(portalAddress)) {
                binding.inputLayoutPortal?.error = getString(R.string.error_input_portal)
            }
            if (!isValidEmail(email)) {
                binding.inputLayoutEmail?.error = getString(R.string.email_input_error)
            }

        }
    }

    private fun checkData(): Boolean {
        return !(portalAddress.isNullOrEmpty() || password.isNullOrEmpty() || email.isNullOrEmpty())
    }

    private fun showError(errorCode: Int) {
        binding.progressBar.isVisible = false
        when (errorCode) {
            StatusCode.CODE_FAILED -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.user_auth_failed), Toast.LENGTH_SHORT
                )
                    .show()
            }

            StatusCode.CODE_EMPTY -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.username_pass_is_empty),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            StatusCode.CODE_USER_NOT_FOUND -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.user_could_not_be_found), Toast.LENGTH_SHORT
                )
                    .show()
            }

            StatusCode.CODE_MANY_LOGIN_ATTEMPTS -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.please_try_again_later),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }
    }

    private fun openDocuments() {
        findNavController().navigate(R.id.action_authFragment_to_documentsFragment)
        binding.progressBar.isVisible = false
    }

    private fun setLoginData() {
        viewModel.setLoginData(
            LoginDataState(
                portalAddress = portalAddress,
                email = email,
                password = password
            )
        )
    }

    private fun isValidPortalAddress(address: String?): Boolean {
        val portalRegex = Regex(
            pattern = "^https://[a-zA-Z0-9.-]+\\.(com|ru|net|org|edu|info|biz|gov|io|co|us|uk)(/.*)?\$"
        )
        return address?.matches(portalRegex) ?: false
    }

    private fun isValidEmail(email: String?): Boolean {
        val emailRegex = Regex(
            pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        )
        return email?.matches(emailRegex) ?: false
    }

}