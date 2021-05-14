package com.naranjo.kristian.pokemonsample.ui.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.naranjo.kristian.pokemonsample.databinding.PokedexItemBinding
import com.naranjo.kristian.pokemonsample.service.Pokemon
import com.naranjo.kristian.pokemonsample.ui.util.CircularAdapter


class PokedexAdapter : CircularAdapter<Pokemon, String, PokemonItemViewHolder>(Pokemon::name) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {
        return PokemonItemViewHolder(
            PokedexItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }


    override fun onBindCircularViewHolder(holder: PokemonItemViewHolder, position: Int) {
        holder.bind(
            getItem(position.toCircularPosition()).getImageUrl()
        )
    }
}

class PokemonItemViewHolder(private val binding: PokedexItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl: String) {
        binding.detailsImage.load(imageUrl)
    }
}