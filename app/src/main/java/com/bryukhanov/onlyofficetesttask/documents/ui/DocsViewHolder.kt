package com.bryukhanov.onlyofficetesttask.documents.ui

import androidx.recyclerview.widget.RecyclerView
import com.bryukhanov.onlyofficetesttask.R
import com.bryukhanov.onlyofficetesttask.databinding.ItemDocumentsBinding
import com.bryukhanov.onlyofficetesttask.documents.domain.model.DocsItem
import com.bryukhanov.onlyofficetesttask.documents.domain.model.File
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Folder

class DocsViewHolder(
    private val binding: ItemDocumentsBinding,
    private val clickListener: DocsAdapter.DocsClickListener,
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DocsItem) {
        when (item) {
            is Folder -> {
                binding.nameDocument.text = item.title
                binding.coverDocument.setImageResource(R.drawable.ic_folder_24)
            }

            is File -> {
                binding.nameDocument.text = item.title
                binding.coverDocument.setImageResource(R.drawable.ic_file_24)
            }
        }
        itemView.setOnClickListener { clickListener.onItemClickListener.invoke(item) }
    }
}