package com.naranjo.kristian.pokemonsample.service

data class Pokemon(val name: String, val url: String) {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
    }
}