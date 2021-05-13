package com.naranjo.kristian.pokemonsample.ui.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naranjo.kristian.pokemonsample.databinding.ActivityPokedexBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexActivity : AppCompatActivity() {

    lateinit var binding: ActivityPokedexBinding

    private val viewModel: PokedexViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.pokemon.observe(this) {
            binding.placeholderText.text = it.joinToString()
        }
    }
}