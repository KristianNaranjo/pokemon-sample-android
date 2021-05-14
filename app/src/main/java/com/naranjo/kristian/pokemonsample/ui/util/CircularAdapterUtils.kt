package com.naranjo.kristian.pokemonsample.ui.util

fun calculateCircularPosition(position: Int, listSize: Int, contentSize: Int): Int {
    val differenceFromMidpoint = listSize/2 - position
    val midpointDifferenceRelativeToSize = differenceFromMidpoint % contentSize
    return (contentSize - midpointDifferenceRelativeToSize) % contentSize
}