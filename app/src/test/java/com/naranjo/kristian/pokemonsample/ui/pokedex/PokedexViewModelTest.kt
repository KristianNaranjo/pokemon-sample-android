package com.naranjo.kristian.pokemonsample.ui.pokedex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.naranjo.kristian.pokemonsample.data.PokemonRepository
import com.naranjo.kristian.pokemonsample.data.Result
import com.naranjo.kristian.pokemonsample.service.Pokemon
import com.naranjo.kristian.pokemonsample.service.PokemonResponse
import com.naranjo.kristian.pokemonsample.ui.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class PokedexViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockPokemonRepository = mockk<PokemonRepository>()

    private val viewModel by lazy { PokedexViewModel(mockPokemonRepository) }

    @Test
    fun `when pokemon response is an error, show error state`() =
        coroutineTestRule.runBlockingTest {
            coEvery { mockPokemonRepository.getPokemon() } returns Result.Error(TimeoutException())
            viewModel.state.observeForever { }

            assertEquals(PokedexViewModel.State.Error, viewModel.state.value)
        }

    @Test
    fun `when pokemon response is empty, emit empty state`() = coroutineTestRule.runBlockingTest {
        coEvery { mockPokemonRepository.getPokemon() } returns Result.Success(MOCK_EMPTY_RESPONSE.results)
        viewModel.state.observeForever { }

        assertEquals(PokedexViewModel.State.Empty, viewModel.state.value)
    }

    @Test
    fun `when pokemon response is successful, emit success state`() =
        coroutineTestRule.runBlockingTest {
            coEvery { mockPokemonRepository.getPokemon() } returns Result.Success(
                MOCK_POKEMON_RESPONSE.results
            )
            viewModel.state.observeForever { }

            assertEquals(
                PokedexViewModel.State.Loaded(MOCK_POKEMON_RESPONSE.results),
                viewModel.state.value
            )
        }

    companion object {
        private val MOCK_POKEMON_RESPONSE = PokemonResponse(
            count = 2,
            results = listOf(
                Pokemon("Bulbasaur", ""),
                Pokemon("Squirtle", "")
            )
        )

        private val MOCK_EMPTY_RESPONSE = PokemonResponse(
            count = 0,
            results = emptyList()
        )
    }
}