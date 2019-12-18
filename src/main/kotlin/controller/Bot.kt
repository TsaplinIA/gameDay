package controller

import model.GameDate

class Bot(private val gameDate: GameDate) {
    //red - даты, при которых проигрываем
    //green - даты, при которых выигрываем
    private val red = mutableListOf<Pair<Int, Int>>()//месяц день
    private val green = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>() // месяц-день, добавить к месяцу-добавить к дню

    init {
        val mainDate = GameDate(11,31)//временный оъект, для построения "карты" ходов бота
        green[11 to 31] = 0 to 0
        do {
            val pairNow = mainDate.month to mainDate.day
            if (!red.contains(pairNow) && !green.containsKey(pairNow)) {
                val daysPossible = mainDate.nextDatesPossible()
                var addInRed = true
                var step = 0 to 0
                daysPossible.forEach {
                    if (red.contains(it.key.month to it.key.day)) {
                        addInRed = false
                        step = it.value
                    }
                }
                if (addInRed) red.add(mainDate.month to mainDate.day)
                else green[pairNow] = step
            }
        } while (mainDate.minusDay())
    }

    fun step() {
//        println("ухожу с ${gameDate.month to gameDate.day}")
        val pairNow = gameDate.month to gameDate.day
        if (green.containsKey(pairNow)) {
            //println("${green[pairNow]}")
            gameDate.plusDay(green[pairNow]!!.second)
            gameDate.plusMouth(green[pairNow]!!.first)
//            println("c клетки зелёной $pairNow Бот сходил ${green[pairNow]}")
        }else if (red.contains(gameDate.month to gameDate.day)) {
            var rnd = (1..2).random()
            while (!gameDate.dayPlusPossible(rnd)) {
                rnd = (1..2).random()
            }
            gameDate.plusDay(rnd)
//            println("c клетки красной $pairNow Бот сходил (0, $rnd)")
        }
    }
}