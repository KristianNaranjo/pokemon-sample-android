package com.naranjo.kristian.pokemonsample.ui.util

import org.junit.Assert.assertEquals
import org.junit.Test

class CircularAdapterUtilsTest {

    // ex: listSize = 11, contentSize = 4
    // positions in RecyclerView:        0 1 2 3 4 5 6 7 8 9 10
    // positions transposed for content: 3 0 1 2 3 0 1 2 3 0 1

    @Test
    fun `position at the middle should be first in list`() {
        val position = calculateCircularPosition(position = 5, listSize = 11, contentSize = 4)
        assertEquals(0, position)
    }

    @Test
    fun `position at one less than the middle should be one less than the content size`() {
        val position = calculateCircularPosition(position = 4, listSize = 11, contentSize = 4)
        assertEquals(3, position)
    }

    @Test
    fun `position at one more than the middle should be one more than the content size`() {
        val position = calculateCircularPosition(position = 6, listSize = 11, contentSize = 4)
        assertEquals(1, position)
    }

    @Test
    fun `position with difference between middle position larger than content size should calculate correctly`() {
        val position = calculateCircularPosition(position = 0, listSize = 11, contentSize = 4)
        assertEquals(3, position)
    }

    @Test
    fun `position with negative difference between middle position larger than content size should calculate correctly`() {
        val position = calculateCircularPosition(position = 10, listSize = 11, contentSize = 4)
        assertEquals(1, position)
    }
}