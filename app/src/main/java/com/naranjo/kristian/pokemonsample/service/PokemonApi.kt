package com.naranjo.kristian.pokemonsample.service

import retrofit2.http.GET

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemon(): PokemonResponse
}