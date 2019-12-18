package model

import java.lang.Exception

class GameDate(var month: Int, var day: Int) {
    val myMonthRange: IntRange = 0..11

    val myDayRangeList: List<IntRange> = listOf(1..31, 1..29, 1..31, 1..30, 1..31, 1..30,
            1..31, 1..31, 1..30, 1..31, 1..30, 1..31)

    init {
        checkDate()
    }

    fun setDate(month: Int, day: Int) {
        this.month = month
        this.day = day
        checkDate()
    }

    fun checkDate(): GameDate {
        if(month !in myMonthRange || day !in myDayRangeList[month]) throw Exception("Uncorrect date: $this")
        return this
    }

    fun plusMouth(count: Int): GameDate {
        month += count
        return checkDate()
    }

    fun plusDay(count: Int): GameDate {
        val lastDay = myDayRangeList[month].last
        val dayNow = day
        if(day + count > lastDay) {
            month++
            day = 0
            plusDay(count - lastDay + dayNow)
        }else day += count
        return checkDate()
    }

    fun minusDay(): Boolean {
        var newDay = day - 1
        if (newDay <= 0 && month - 1 !in myMonthRange) return false
        if (newDay <= 0) setDate(month - 1, myDayRangeList[month - 1].last)
        else setDate(month, newDay)
        return true
    }

    fun nextDatesPossible(): Map<GameDate, Pair<Int, Int>> {
        val result = mutableMapOf<GameDate, Pair<Int, Int>>()
        if (dayPlusPossible(1)) result[GameDate(month, day).plusDay(1)] = 0 to 1
        if (dayPlusPossible(2)) result[GameDate(month, day).plusDay(2)] = 0 to 2
        if (monthPlusPossible(1)) result[GameDate(month, day).plusMouth(1)] = 1 to 0
        if (monthPlusPossible(2)) result[GameDate(month, day).plusMouth(2)] = 2 to 0
        return result
    }

    fun dayPlusPossible(count: Int): Boolean =
            (day + count) in myDayRangeList[month] || month != myMonthRange.last

    fun monthPlusPossible(count: Int): Boolean = (month + count) in myMonthRange && day in myDayRangeList[month + count]

    fun victoryNow(): Boolean =
            (day == myDayRangeList[month].last) && (month == myMonthRange.last)


    override fun toString(): String {
        return "${month + 1}.$day"
    }
}