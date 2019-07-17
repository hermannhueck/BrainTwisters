@file:JvmName("KtSuggestions2")

package suggestions

private val MAX_WORDS = 13

data class Suggestion2(val text: String) {
    override fun toString() = text
}

private val INPUT = listOf("The", "moon", "is", "not", "a", "planet", "and", "also", "not", "a", "star", ".",
        "But", ",", "I", "digress", "we", "people", "are", "so", "irrational", ".")

private val STOPWORDS = setOf("The", "a", "is")

fun main(args: Array<String>) {
    makeSuggestions(maxWords(args), INPUT, STOPWORDS).forEach(::println)
}


private fun maxWords(args: Array<String>): Int {
    if (args.isEmpty())
        return MAX_WORDS
    try {
        val maxWords = Integer.parseInt(args[0])
        return if (maxWords < 1) 1 else maxWords
    } catch (e: NumberFormatException) {
        return MAX_WORDS
    }
}

private fun makeSuggestions(maxWords: Int,
                            words: List<String>,
                            stopWords: Set<String>): List<Suggestion2> {
    val purged = words
            .filter { e -> !stopWords.contains(e) }
            .filter { e -> e.matches("\\w+".toRegex()) }
    val maxWords2 = if (maxWords > purged.size) purged.size else maxWords
    println("----- maxWords = " + maxWords2)
    println("----- purged.size = " + purged.size)
    println("----- purged = " + purged)

    return suggestions(maxWords2, purged)
            .map(::Suggestion2)
}

private fun suggestions(maxWords: Int, words: List<String>): List<String> =
    (0..words.size - maxWords + 1 - 1)
            .flatMap { index ->
                combinations(index, maxWords, words)
            }

private fun combinations(index: Int, maxWords: Int, words: List<String>): List<String> =
        (index..index + maxWords - 1)
                .map { i -> words[i] }
                .fold(emptyList<String>()) { acc, word ->
                    if (acc.isEmpty())
                        listOf(word)
                    else
                        acc.plus("${acc.last()} $word")
                }

