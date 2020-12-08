package se.vhaga.aoc20_04

import se.vhaga.aoc20_util.FileUtil

fun main(args: Array<String>) {
    AOC20_04.run()
}

object AOC20_04 : Runnable {

    private val input by lazy {
        val inputFile = FileUtil.readFile("/AOC20_04/src/main/assets", "input.txt")
        inputFile.readLines()
    }

    private val validEyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    override fun run() {
        println("The answer to part one is: ${partOne()}")
        println("The answer to part two is: ${partTwo()}")
    }

    private fun partOne(): Int {
        val passports = mutableListOf<MutableMap<String, String>>(mutableMapOf())
        input.forEach { line ->
            if (line.isNotBlank()) {
                line.split(" ").forEach { pair ->
                    pair.split(":").apply {
                        passports.last()[first()] = last()
                    }
                }
            } else {
                passports.add(mutableMapOf())
            }
        }

        return passports.count {
            if (it.size < 7) false
            else !(it.size == 7 && it.containsKey("cid"))
        }
    }

    private fun partTwo(): Int {
        val passports = mutableListOf<MutableMap<String, String>>(mutableMapOf())
        input.forEach { line ->
            if (line.isBlank()) {
                passports.add(mutableMapOf())
            } else {
                line.split(" ").forEach { pair ->
                    pair.split(":").apply {
                        passports.last()[first()] = last()
                    }
                }
            }
        }

        return passports.count {
            if (it.size < 7) return@count false
            else if (it.size == 7 && it.containsKey("cid")) return@count false

            it.forEach { (key, value) ->
                val isValid = when (key) {
                    "byr" -> {
                        validateBirthYear(value)
                    }
                    "iyr" -> {
                        validateIssueYear(value)
                    }
                    "eyr" -> {
                        validateExpirationYear(value)
                    }
                    "hgt" -> {
                        validateHeight(value)
                    }
                    "hcl" -> {
                        validateHairColor(value)
                    }
                    "ecl" -> {
                        validateEyeColor(value)
                    }
                    "pid" -> {
                        validatePassportId(value)
                    }
                    "cid" -> {
                        true
                    }
                    else -> false
                }

                if (!isValid) return@count false
            }

            true
        }
    }

    private fun validateBirthYear(value: String): Boolean {
        return value.length == 4 &&
                value.toInt() in 1920..2002
    }

    private fun validateIssueYear(value: String): Boolean {
        return value.length == 4 &&
                value.toInt() in 2010..2020
    }

    private fun validateExpirationYear(value: String): Boolean {
        return value.length == 4 &&
                value.toInt() in 2020..2030
    }

    private fun validateHeight(value: String): Boolean {
        return when {
            value.endsWith("cm") -> {
                value.dropLast(2).toInt() in 150..193
            }
            value.endsWith("in") -> {
                value.dropLast(2).toInt() in 59..76
            }
            else -> false
        }
    }

    private fun validateHairColor(value: String): Boolean {
        return value.length == 7 && Regex("#[0-9a-f]{6}").matches(value)
    }

    private fun validateEyeColor(value: String): Boolean {
        return validEyeColors.contains(value)
    }

    private fun validatePassportId(value: String): Boolean {
        return value.length == 9 && value.toDoubleOrNull() != null
    }
}