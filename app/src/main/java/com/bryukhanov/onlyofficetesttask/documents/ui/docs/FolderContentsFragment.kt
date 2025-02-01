package com.bryukhanov.onlyofficetesttask.documents.ui.docs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bryukhanov.onlyofficetesttask.BaseFragment
import com.bryukhanov.onlyofficetesttask.R
import com.bryukhanov.onlyofficetesttask.databinding.FragmentFolderContentsBinding
import com.bryukhanov.onlyofficetesttask.documents.domain.model.DocsItem
import com.bryukhanov.onlyofficetesttask.documents.ui.DocsAdapter
import com.bryukhanov.onlyofficetesttask.documents.ui.DocsState
import org.koin.androidx.viewmodel.ext.android.viewModel

class FolderContentsFragment : BaseFragment<FragmentFolderContentsBinding>() {

    private val viewModel by viewModel<DocsViewModel>()

    private val adapter by lazy {
        DocsAdapter(object : DocsAdapter.DocsClickListener {
            override val onItemClickListener: (DocsItem) -> Unit
                get() = {}
        })
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentFolderContentsBinding {
        return FragmentFolderContentsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: FolderContentsFragmentArgs by navArgs()
        val folder = args.folder

        viewModel.openFolderContent(folder.id)

        binding.rvDocuments.adapter = adapter

        binding.nameFolder.text = folder.title

        viewModel.getStateFolderContent().observe(viewLifecycleOwner) { state ->
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

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.clearAdapter()
    }

}