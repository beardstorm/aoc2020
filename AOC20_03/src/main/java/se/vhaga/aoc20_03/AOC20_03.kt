package se.vhaga.aoc20_03

import se.vhaga.aoc20_util.FileUtil

fun main(args: Array<String>) {
    AOC20_03.run()
}

object AOC20_03 : Runnable {

    private val input by lazy {
        val inputFile = FileUtil.readFile("/AOC20_03/src/main/assets", "input.txt")
        inputFile.readLines()
    }


    override fun run() {
        println("The answer to part one is: ${partOne()}")
        println("The answer to part two is: ${partTwo()}")
    }

    private fun partOne(): Int {
        return input.filterIndexed { index, line ->
            line[index * 3 % line.length].toString() == "#"
        }.count()
    }

    private fun partTwo(): Long {
        var count1 = 0L
        var count2 = 0L
        var count3 = 0L
        var count4 = 0L
        var count5 = 0L

        input.forEachIndexed { index, line ->
            if (line[index % line.length].toString() == "#") count1++
            if (line[index * 3 % line.length].toString() == "#") count2++
            if (line[index * 5 % line.length].toString() == "#") count3++
            if (line[index * 7 % line.length].toString() == "#") count4++
            if (index % 2 == 0 && line[(index / 2) % line.length].toString() == "#") count5++
        }

        return count1 * count2 * count3 * count4 * count5
    }
}