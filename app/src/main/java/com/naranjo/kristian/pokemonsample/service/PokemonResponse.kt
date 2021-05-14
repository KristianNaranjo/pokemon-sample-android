package com.naranjo.kristian.pokemonsample.service

import com.squareup.moshi.Json

data class PokemonResponse(
    @Json(name = "count")
    val count: Long,
    @Json(name = "next")
    val nextPageUrl: String? = null,
    @Json(name = "previous")
    val previousPageUrl: String? = null,
    @Json(name = "results")
    val results: List<Pokemon> = emptyList()
)