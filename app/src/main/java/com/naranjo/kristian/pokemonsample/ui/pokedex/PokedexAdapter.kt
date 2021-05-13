package com.naranjo.kristian.pokemonsample.ui.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.naranjo.kristian.pokemonsample.R
import com.naranjo.kristian.pokemonsample.databinding.PokedexItemBinding
import com.naranjo.kristian.pokemonsample.service.Pokemon


class PokedexAdapter : ListAdapter<Pokemon, RecyclerView.ViewHolder>(PokedexDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.pokedex_item -> PokemonItemViewHolder(
                PokedexItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PokemonItemViewHolder -> holder.bind(getItem(position).getImageUrl())
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Pokemon -> R.layout.pokedex_item
            else -> throw IllegalStateException()
        }
    }
}

class PokemonItemViewHolder(private val binding: PokedexItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl: String) {
        binding.detailsImage.load(imageUrl)
    }
}

class PokedexDiffUtil : DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
        oldItem == newItem

}