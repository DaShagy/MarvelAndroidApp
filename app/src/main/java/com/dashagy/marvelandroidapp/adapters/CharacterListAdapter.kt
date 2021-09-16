package com.dashagy.marvelandroidapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dashagy.marvelandroidapp.databinding.ListMarvelCharactersBinding

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private var dataset = mutableListOf<String>()

    fun updateDataset(dataset: MutableList<String>){
        this.dataset = dataset
    }

    class CharacterViewHolder(val binding: ListMarvelCharactersBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ListMarvelCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        with (holder){
            with(dataset[position]){
                binding.name.text = this
            }
        }
    }

    override fun getItemCount() = dataset.size
}
