package com.naranjo.kristian.pokemonsample

import android.app.Application
import com.naranjo.kristian.pokemonsample.data.PokemonRepository
import com.naranjo.kristian.pokemonsample.data.PokemonRepositoryImpl
import com.naranjo.kristian.pokemonsample.service.PokemonApiBuilder
import com.naranjo.kristian.pokemonsample.ui.pokedex.PokedexViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class PokemonSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokemonSampleApplication)
            modules(pokemonSampleModule)
        }
    }

    private val pokemonSampleModule = module {
        single { PokemonApiBuilder.build() }
        single<PokemonRepository> { PokemonRepositoryImpl(get()) }
        viewModel { PokedexViewModel(get()) }
    }
}