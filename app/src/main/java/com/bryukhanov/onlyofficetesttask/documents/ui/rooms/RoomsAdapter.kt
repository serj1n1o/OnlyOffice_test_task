package com.bryukhanov.onlyofficetesttask.documents.ui.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bryukhanov.onlyofficetesttask.databinding.ItemDocumentsBinding
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Room

class RoomsAdapter : RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>() {

    private val items = mutableListOf<Room>()

    fun setData(rooms: List<Room>) {
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val binding =
            ItemDocumentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomsViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class RoomsViewHolder(private val binding: ItemDocumentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Room) {
            binding.nameDocument.text = item.title
        }
    }
}