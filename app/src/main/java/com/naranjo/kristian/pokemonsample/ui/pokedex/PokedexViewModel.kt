package com.naranjo.kristian.pokemonsample.ui.pokedex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naranjo.kristian.pokemonsample.data.PokemonRepository
import com.naranjo.kristian.pokemonsample.data.Result
import com.naranjo.kristian.pokemonsample.service.Pokemon
import kotlinx.coroutines.launch

class PokedexViewModel(pokemonRepository: PokemonRepository) : ViewModel() {

    val pokemon = MutableLiveData<List<Pokemon>>()

    init {
        viewModelScope.launch {
            val result = pokemonRepository.getPokemon()

            if (result is Result.Success) {
                pokemon.postValue(result.data)
            }
        }
    }
}