package com.naranjo.kristian.pokemonsample.ui.pokedex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naranjo.kristian.pokemonsample.data.PokemonRepository
import com.naranjo.kristian.pokemonsample.data.Result
import com.naranjo.kristian.pokemonsample.service.Pokemon
import kotlinx.coroutines.launch

class PokedexViewModel(pokemonRepository: PokemonRepository) : ViewModel() {

    val state = MutableLiveData<State>(State.Loading)

    init {
        viewModelScope.launch {
            val result = pokemonRepository.getPokemon()

            state.postValue(
                when (result) {
                    is Result.Success -> {
                        when {
                            result.data.isNotEmpty() -> State.Loaded(result.data)
                            else -> State.Empty
                        }
                    }
                    is Result.Error -> State.Error
                }
            )
        }
    }

    sealed class State {
        data class Loaded(val pokemon: List<Pokemon>) : State()
        object Loading : State()
        object Error : State()
        object Empty : State()
    }
}