package com.bryukhanov.onlyofficetesttask.documents.ui.trash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.R
import com.bryukhanov.onlyofficetesttask.databinding.FragmentTrashBinding
import com.bryukhanov.onlyofficetesttask.documents.domain.model.DocsItem
import com.bryukhanov.onlyofficetesttask.documents.ui.DocsAdapter
import com.bryukhanov.onlyofficetesttask.documents.ui.DocsState
import org.koin.androidx.viewmodel.ext.android.viewModel


class TrashFragment : BaseFragment<FragmentTrashBinding>() {

    private val viewModel by viewModel<TrashViewModel>()

    private val adapter by lazy {
        DocsAdapter(object : DocsAdapter.DocsClickListener {
            override val onItemClickListener: (DocsItem) -> Unit
                get() = {}
        })
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentTrashBinding {
        return FragmentTrashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTrash()

        binding.rvTrashDocs.adapter = adapter

        viewModel.getStateDocs().observe(viewLifecycleOwner) { state ->

            when (state) {
                is DocsState.Content -> {
                    adapter.setData(state.folders, state.files)
                }

                DocsState.Empty -> {
                    adapter.clearAdapter()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.empty_data),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                DocsState.Error -> {
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
        adapter.clearAdapter()
    }

}