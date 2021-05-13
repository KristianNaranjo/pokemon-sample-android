package com.naranjo.kristian.pokemonsample.data

import com.naranjo.kristian.pokemonsample.service.Pokemon
import com.naranjo.kristian.pokemonsample.service.PokemonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.lang.Exception

interface PokemonRepository {
    suspend fun getPokemon(): Result<List<Pokemon>>
}

class PokemonRepositoryImpl(private val pokemonApi: PokemonApi): PokemonRepository {

    override suspend fun getPokemon(): Result<List<Pokemon>> = withContext(Dispatchers.IO) {
            try {
                withTimeout(REQUEST_TIMEOUT) {
                    Result.Success(pokemonApi.getPokemon().results)
                }
            } catch (error: Exception) {
                Result.Error(error)
            }
        }

    companion object {
        private const val REQUEST_TIMEOUT = 5000L
    }
}