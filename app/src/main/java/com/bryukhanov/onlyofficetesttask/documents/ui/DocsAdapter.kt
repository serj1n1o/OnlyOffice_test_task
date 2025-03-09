package com.bryukhanov.onlyofficetesttask.documents.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bryukhanov.onlyofficetesttask.databinding.ItemDocumentsBinding
import com.bryukhanov.onlyofficetesttask.documents.domain.model.DocsItem
import com.bryukhanov.onlyofficetesttask.documents.domain.model.File
import com.bryukhanov.onlyofficetesttask.documents.domain.model.Folder

class DocsAdapter(private val clickListener: DocsClickListener) :
    RecyclerView.Adapter<DocsViewHolder>() {

    private val items = mutableListOf<DocsItem>()

    interface DocsClickListener {
        val onItemClickListener: ((DocsItem) -> Unit)
    }

    fun setData(folders: List<Folder>, files: List<File>) {
        items.clear()
        items.addAll(folders)
        items.addAll(files)
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocsViewHolder {
        val binding =
            ItemDocumentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DocsViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DocsViewHolder, position: Int) {
        holder.bind(items[position])
    }

}