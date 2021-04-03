package com.a.androidmovieapp.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a.androidmovieapp.databinding.ItemSpecieBinding
import com.a.androidmovieapp.domain.models.SpecieModel

class SpeciesAdapter(private val speciesList: List<SpecieModel>) :
    RecyclerView.Adapter<SpeciesAdapter.ViewHolder>() {
    inner class ViewHolder(private val view: ItemSpecieBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindTo(specie: SpecieModel) {
            with(view) {
                itemNameValue.text = specie.name
                itemLanguageValue.text = specie.language
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesAdapter.ViewHolder =
        ViewHolder(
            ItemSpecieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SpeciesAdapter.ViewHolder, position: Int) {
        holder.bindTo(speciesList[position])
    }

    override fun getItemCount()= speciesList.size




}