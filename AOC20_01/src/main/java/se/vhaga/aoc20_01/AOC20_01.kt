package se.vhaga.aoc20_01

import se.vhaga.aoc20_util.FileUtil

fun main(args: Array<String>) {
    AOC20_01.run()
}

object AOC20_01 : Runnable {

    private val input by lazy {
        val inputFile = FileUtil.readFile("/AOC20_01/src/main/assets", "input.txt")
        inputFile.readLines().mapNotNull { it.toIntOrNull() }
    }

    override fun run() {
        println("The answer to part one is: ${partOne(input)}")
        println("The answer to part two is: ${partTwo(input)}")
    }

    private fun partOne(input: List<Int>): Int {
        input.forEach { outer ->
            input.filter { it != outer }.forEach { inner ->
                if (outer + inner == 2020) {
                    return outer * inner
                }
            }
        }

        return 0
    }

    private fun partTwo(input: List<Int>): Int {
        input.forEach { outer ->
            input.filter { it != outer }.forEach { inner ->
                input.filter { it != outer || it != inner }.forEach { innermost ->
                    if (outer + inner + innermost == 2020) {
                        return outer * inner * innermost
                    }
                }
            }
        }

        return 0
    }
}