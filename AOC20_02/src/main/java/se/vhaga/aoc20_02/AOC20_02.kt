package se.vhaga.aoc20_02

import se.vhaga.aoc20_util.FileUtil

fun main(args: Array<String>) {
    AOC20_02.run()
}

object AOC20_02 : Runnable {

    private val input by lazy {
        val inputFile = FileUtil.readFile("/AOC20_02/src/main/assets", "input.txt")
        inputFile.readLines()
    }


    override fun run() {
        println("The answer to part one is: ${partOne()}")
        println("The answer to part two is: ${partTwo()}")
    }

    private fun partOne(): Int {
        return input.count { line ->
            val min: Int
            val max: Int
            val char: Char
            val isValid: Boolean

            line.split("-").apply {
                min = first().toInt()
                get(1).split(" ").apply {
                    max = first().toInt()
                    char = get(1)[0]

                    val charCount = get(2).count { it == char }
                    isValid = charCount in min..max
                }
            }

            isValid
        }
    }

    private fun partTwo(): Int {
        return input.count { line ->
            val i1: Int
            val i2: Int
            val char: Char
            val isValid: Boolean

            line.split("-").apply {
                i1 = first().toInt() - 1
                get(1).split(" ").apply {
                    i2 = first().toInt() - 1
                    char = get(1)[0]

                    val char1 = get(2).getOrNull(i1)
                    val char2 = get(2).getOrNull(i2)
                    isValid = if (char == char1) {
                        char != char2
                    } else {
                        char == char2
                    }
                }
            }

            isValid
        }
    }
}