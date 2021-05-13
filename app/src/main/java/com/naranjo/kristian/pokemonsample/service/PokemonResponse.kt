package com.naranjo.kristian.pokemonsample.service

import com.squareup.moshi.Json

data class PokemonResponse(
    @Json(name = "count")
    val count: Long,
    @Json(name = "next")
    val nextPageUrl: String?,
    @Json(name = "previous")
    val previousPageUrl: String?,
    @Json(name = "results")
    val results: List<Pokemon> = emptyList()
)