package com.naranjo.kristian.pokemonsample.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PokemonApiBuilder {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    fun build(): PokemonApi = retrofit.create(PokemonApi::class.java)
}