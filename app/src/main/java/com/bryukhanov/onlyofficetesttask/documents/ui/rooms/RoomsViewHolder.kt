package com.bryukhanov.onlyofficetesttask.documents.ui.rooms

import androidx.recyclerview.widget.RecyclerView
import com.bryukhanov.onlyofficetesttask.databinding.ItemDocumentsBinding
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Room

class RoomsViewHolder(private val binding: ItemDocumentsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Room) {
        binding.nameDocument.text = item.title
    }
}