package se.vhaga.aoc20_05

import se.vhaga.aoc20_util.FileUtil
import kotlin.math.floor

fun main(args: Array<String>) {
    AOC20_05.run()
}

object AOC20_05 : Runnable {

    private val input by lazy {
        val inputFile = FileUtil.readFile("/AOC20_05/src/main/assets", "input.txt")
        inputFile.readLines()
    }

    override fun run() {
        println("The answer to part one is: ${partOne()}")
        println("The answer to part two is: ${partTwo()}")
    }

    private fun partOne(): Int {
        var highestSeatId = 0

        input.forEach { line ->
            val rows = line.take(7).toMutableList()
            var values = (0..127).toList()
            do {
                val char = rows.removeFirst()
                values = takeHalf(values, char)
            } while (values.size > 1)
            val row = values.first()

            val columns = line.takeLast(3).toMutableList()
            values = (0..7).toList()
            do {
                val char = columns.removeFirst()
                values = takeHalf(values, char)
            } while (values.size > 1)
            val column = values.first()

            val seatId = row * 8 + column
            if (seatId > highestSeatId)
                highestSeatId = seatId
        }

        return highestSeatId
    }

    private fun partTwo(): Int {
        val seatIds = input.map { line ->
            val rows = line.take(7).toMutableList()
            var values = (0..127).toList()
            do {
                val char = rows.removeFirst()
                values = takeHalf(values, char)
            } while (values.size > 1)
            val row = values.first()

            val columns = line.takeLast(3).toMutableList()
            values = (0..7).toList()
            do {
                val char = columns.removeFirst()
                values = takeHalf(values, char)
            } while (values.size > 1)
            val column = values.first()

            row * 8 + column
        }

        val sorted = seatIds.sortedBy { it }
        val lowest = sorted.first()
        return sorted.withIndex().first {
            it.value - lowest - it.index != 0
        }.value - 1
    }

    private fun takeHalf(values: List<Int>, char: Char): List<Int> {
        val sets = values.withIndex().partition {
            val midIndex = floor(values.size / 2.0).toInt()
            it.index < midIndex
        }
        return if (char == 'F' || char == 'L')
            sets.first.map { it.value }
        else
            sets.second.map { it.value }
    }
}