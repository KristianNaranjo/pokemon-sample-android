package com.naranjo.kristian.pokemonsample.ui.pokedex

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.naranjo.kristian.pokemonsample.R
import com.naranjo.kristian.pokemonsample.databinding.ActivityPokedexBinding
import com.naranjo.kristian.pokemonsample.service.Pokemon
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
            val isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
            orientation = getViewPagerOrientation(isPortrait)
            adapter = pokedexAdapter
            offscreenPageLimit = 3
            setPageTransformer(
                CompositePageTransformer().apply {
                    val offsetPx = resources.getDimensionPixelOffset(R.dimen.details_image_offset)
                    val marginPx =
                        resources.getDimensionPixelOffset(R.dimen.details_image_page_margin)
                    addTransformer(
                        TranslationTransformer(
                            offsetPx,
                            marginPx,
                            getViewPagerOrientation(isPortrait)
                        )
                    )
                    addTransformer(AlphaTransformer(.6f))
                    addTransformer(ScaleTransformer(.4f))
                }
            )
        }

        viewModel.state.observe(this) { state ->
            binding.resetState()

            when (state) {
                PokedexViewModel.State.Loading -> binding.showLoadingState()
                is PokedexViewModel.State.Loaded -> binding.showLoadedState(
                    state.pokemon,
                    pokedexAdapter
                )
                PokedexViewModel.State.Error -> binding.showErrorState()
                PokedexViewModel.State.Empty -> binding.showEmptyState()
            }
        }
    }

    private fun getViewPagerOrientation(isPortrait: Boolean): Int {
        return when {
            isPortrait -> ViewPager2.ORIENTATION_VERTICAL
            else -> ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun ActivityPokedexBinding.resetState() {
        pokemonList.isVisible = false
        loadingIndicator.isVisible = false
        errorMessage.isVisible = false
        emptyMessage.isVisible = false
    }

    private fun ActivityPokedexBinding.showLoadingState() {
        loadingIndicator.isVisible = true
    }

    private fun ActivityPokedexBinding.showLoadedState(
        pokemon: List<Pokemon>,
        adapter: PokedexAdapter
    ) {
        pokemonList.isVisible = true

        pokemonList.setCurrentItem(adapter.getCenterPage(), false)
        adapter.submitList(pokemon)
    }

    private fun ActivityPokedexBinding.showErrorState() {
        errorMessage.isVisible = true
    }

    private fun ActivityPokedexBinding.showEmptyState() {
        emptyMessage.isVisible = true
    }
}