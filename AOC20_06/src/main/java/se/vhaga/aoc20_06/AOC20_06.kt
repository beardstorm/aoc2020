package se.vhaga.aoc20_06

import se.vhaga.aoc20_util.FileUtil

fun main(args: Array<String>) {
    AOC20_06.run()
}

object AOC20_06 : Runnable {

    private val input by lazy {
        val inputFile = FileUtil.readFile("/AOC20_06/src/main/assets", "input.txt")
        inputFile.readLines()
    }

    override fun run() {
        println("The answer to part one is: ${partOne()}")
        println("The answer to part two is: ${partTwo()}")
    }

    private fun partOne(): Int {
        var count = 0
        val groupAnswers = mutableListOf("")
        input.forEach { line ->
            if (line.isBlank()) {
                count += groupAnswers[groupAnswers.lastIndex].length
                groupAnswers.add("")
                return@forEach
            }
            val answers = groupAnswers.last()
            groupAnswers[groupAnswers.lastIndex] = if (answers.isNotBlank()) {
                answers + line.filterNot { answers.contains(it) }
            } else {
                line
            }
        }
        count += groupAnswers[groupAnswers.lastIndex].length
        return count
    }

    private fun partTwo(): Int {
        val groupAnswers = mutableListOf("")
        var hasInitialInput = false
        input.forEach { line ->
            if (line.isBlank()) {
                hasInitialInput = false
                groupAnswers.add("")
                return@forEach
            }

            groupAnswers[groupAnswers.lastIndex] = if (hasInitialInput) {
                groupAnswers[groupAnswers.lastIndex].filter { line.contains(it) }
            } else {
                hasInitialInput = true
                line
            }
        }

        return groupAnswers.sumBy { it.length }
    }
}