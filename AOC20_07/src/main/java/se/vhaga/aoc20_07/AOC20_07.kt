package se.vhaga.aoc20_07

import se.vhaga.aoc20_util.FileUtil

fun main(args: Array<String>) {
    AOC20_07.run()
}

object AOC20_07 : Runnable {

    private val input by lazy {
        val inputFile = FileUtil.readFile("/AOC20_07/src/main/assets", "input.txt")
        inputFile.readLines()
    }

    private lateinit var rules: List<BagRule>

    override fun run() {
        rules = input.map { line ->
            val split = line.split(" bags contain ")
            val key = split.first()
            val contentString = split.last().split(", ")
            val content = contentString.mapNotNull {
                val words = it.split(" ")
                val amount = words.first()
                if (amount != "no") {
                    val contentKey = words.drop(1).dropLast(1).reduce { acc, s ->
                        "$acc $s"
                    }.trim()
                    Content(
                        contentKey,
                        amount.toInt()
                    )
                } else {
                    null
                }
            }

            BagRule(key, content)
        }

        println("The answer to part one is: ${partOne()}")
        println("The answer to part two is: ${partTwo()}")
    }

    private fun partOne(): Int {
        return rules.count { rule ->
            rule.bag != "shiny golden" && canContainShinyBag(rule)
        }
    }

    private fun partTwo(): Int {
        val rule = rules.single { it.bag == "shiny gold" }
        return rule.allowedContent.sumBy { containsBags(it) }
    }

    private fun canContainShinyBag(rule: BagRule): Boolean {
        return rule.allowedContent.any { it.bag == "shiny gold" } ||
                rule.allowedContent.any { allowed ->
                    canContainShinyBag(rules.single { it.bag == allowed.bag })
                }
    }

    private fun containsBags(content: Content): Int {
        val rule = rules.single { it.bag == content.bag }
        return content.amount + content.amount * rule.allowedContent.sumBy { containsBags(it) }
    }
}

data class BagRule(
    val bag: String,
    val allowedContent: List<Content>
)

data class Content(
    val bag: String,
    val amount: Int
)