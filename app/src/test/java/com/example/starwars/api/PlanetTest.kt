package com.example.starwars.api

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PlanetTest {
    private lateinit var planet: Planet

    @Before
    fun setUp() {
        planet= Planet()
    }

    @After
    fun tearDown() {
        planet.name = "Kamino"
    }

    @Test
    fun getName() {
        assertEquals("Kamino", planet.name)
    }
}