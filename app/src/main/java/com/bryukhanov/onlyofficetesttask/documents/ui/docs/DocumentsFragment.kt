package com.bryukhanov.onlyofficetesttask.documents.ui.docs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.R
import com.bryukhanov.onlyofficetesttask.databinding.FragmentDocumentsBinding
import com.bryukhanov.onlyofficetesttask.documents.domain.model.DocsItem
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Folder
import com.bryukhanov.onlyofficetesttask.documents.ui.DocsAdapter
import com.bryukhanov.onlyofficetesttask.documents.ui.DocsState
import org.koin.androidx.viewmodel.ext.android.viewModel

open class DocumentsFragment : BaseFragment<FragmentDocumentsBinding>() {

    private val docsAdapter by lazy {
        DocsAdapter(object : DocsAdapter.DocsClickListener {
            override val onItemClickListener: (DocsItem) -> Unit
                get() = { item ->
                    if (item is Folder) {
                        openFolderContent(item)
                    }
                }
        })
    }

    private val viewModel by viewModel<DocsViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDocumentsBinding {
        return FragmentDocumentsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDocuments()

        binding.rvDocuments.adapter = docsAdapter

        viewModel.getStateDocs().observe(viewLifecycleOwner) { state ->
            when (state) {
                is DocsState.Content -> {
                    docsAdapter.setData(state.folders, state.files)
                }

                DocsState.Empty -> {

                    docsAdapter.setData(emptyList(), emptyList())
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

    private fun openFolderContent(folder: Folder) {
        val direction =
            DocumentsFragmentDirections.actionDocumentsFragmentToFolderContentsFragment(folder)
        findNavController().navigate(direction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        docsAdapter.clearAdapter()
    }

}