package com.naranjo.kristian.pokemonsample.ui.util

import org.junit.Assert.assertEquals
import org.junit.Test

class CircularAdapterUtilsTest {

    @Test
    fun testPosition() {
        val position = calculateCircularPosition(11, 5, 4)
        assertEquals(0, position)

        val position1 = calculateCircularPosition(11, 4, 4)
        assertEquals(3, position1)

        val position2 = calculateCircularPosition(11, 6, 4)
        assertEquals(1, position2)

        val position3 = calculateCircularPosition(11, 0, 4)
        assertEquals(3, position3)

        val position4 = calculateCircularPosition(11, 10, 4)
        assertEquals(1, position4)
    }
}