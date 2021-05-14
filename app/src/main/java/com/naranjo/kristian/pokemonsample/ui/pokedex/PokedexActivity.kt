package com.naranjo.kristian.pokemonsample.ui.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.naranjo.kristian.pokemonsample.R
import com.naranjo.kristian.pokemonsample.databinding.ActivityPokedexBinding
import com.naranjo.kristian.pokemonsample.ui.util.AlphaTransformer
import com.naranjo.kristian.pokemonsample.ui.util.ScaleTransformer
import com.naranjo.kristian.pokemonsample.ui.util.TranslationTransformer
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexActivity : AppCompatActivity() {

    lateinit var binding: ActivityPokedexBinding

    private val viewModel: PokedexViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokedexAdapter = PokedexAdapter()
        binding.pokemonList.apply {
            adapter = pokedexAdapter
            offscreenPageLimit = 3
            setPageTransformer(CompositePageTransformer().apply {
                val offsetPx = resources.getDimensionPixelOffset(R.dimen.details_image_offset)
                val marginPx = resources.getDimensionPixelOffset(R.dimen.details_image_page_margin)
                addTransformer(
                    TranslationTransformer(
                        offsetPx,
                        marginPx,
                        ViewPager2.ORIENTATION_VERTICAL
                    )
                )
                addTransformer(AlphaTransformer(.6f))
                addTransformer(ScaleTransformer(.4f))
            })
        }

        viewModel.pokemon.observe(this) {
            binding.pokemonList.setCurrentItem(pokedexAdapter.getCenterPage(), false)
            pokedexAdapter.submitList(it)
        }
    }
}