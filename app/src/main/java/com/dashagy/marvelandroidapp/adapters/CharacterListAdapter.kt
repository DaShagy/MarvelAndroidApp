package com.dashagy.marvelandroidapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.marvelandroidapp.databinding.ListMarvelCharactersBinding

class CharacterListAdapter (private val listener: (MarvelCharacter) -> Unit )
    : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private var dataset = mutableListOf<MarvelCharacter>()

    fun updateDataset(dataset: MutableList<MarvelCharacter>){
        this.dataset = dataset
    }

    class CharacterViewHolder(
        val binding: ListMarvelCharactersBinding,
        val onClickItem: (Int) -> Unit
    )
        : RecyclerView.ViewHolder(binding.root){
            init {
                binding.root.setOnClickListener {
                    onClickItem(absoluteAdapterPosition)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ListMarvelCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding) { listener(dataset[it]) }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        with (holder){
            with(dataset[position]){
                binding.name.text = this.name
            }
        }
    }

    override fun getItemCount() = dataset.size
}
