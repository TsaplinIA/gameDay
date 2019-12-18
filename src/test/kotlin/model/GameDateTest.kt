package model

import org.junit.Test

import org.junit.Assert.*

class GameDateTest {

    @Test
    fun plusMouth() {
        val date = GameDate(0,1)
        date.plusMouth(2)
        assertEquals(2,date.month)
        date.plusMouth(1)
        assertEquals(3,date.month)
    }

    @Test
    fun plusDay() {
        val date = GameDate(0,1)
        date.plusDay(2)
        assertEquals(3,date.day)
        date.plusDay(1)
        assertEquals(4,date.day)
    }

    @Test
    fun minusDay() {
        val date = GameDate(11,30)
        date.minusDay()
        assertEquals(29, date.day)
        assertEquals(11, date.month)
        date.setDate(11,1)
        date.minusDay()
        assertEquals(30, date.day)
        assertEquals(10, date.month)
    }

    @Test
    fun nextDatesPossible() {
        val date = GameDate(0,1)
        assertEquals(4, date.nextDatesPossible().size)
        date.setDate(11,31)
        assertEquals(0, date.nextDatesPossible().size)
        date.setDate(10,30)
        assertEquals(3,date.nextDatesPossible().size)
    }

    @Test
    fun dayPlusPossible() {
        val date = GameDate(0, 1)
        assertTrue(date.dayPlusPossible(1))
        assertTrue(date.dayPlusPossible(2))
        date.setDate(10, 29)
        assertTrue(date.dayPlusPossible(1))
        assertTrue(date.dayPlusPossible(2))
        date.setDate(11,30)
        assertTrue(date.dayPlusPossible(1))
        assertFalse(date.dayPlusPossible(2))
    }

    @Test
    fun monthPlusPossible() {
        val date = GameDate(0, 1)
        assertTrue(date.monthPlusPossible(1))
        assertTrue(date.monthPlusPossible(2))
        date.setDate(10,29)
        assertTrue(date.monthPlusPossible(1))
        assertFalse(date.monthPlusPossible(2))
        date.setDate(9, 31)
        assertFalse(date.monthPlusPossible(1))
        assertTrue(date.monthPlusPossible(2))
    }
}