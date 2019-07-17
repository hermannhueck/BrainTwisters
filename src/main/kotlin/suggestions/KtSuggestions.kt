@file:JvmName("KtSuggestions")

package suggestions


private val MAX_WORDS = 13

private data class Suggestion(val text: String) {
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
                            stopWords: Set<String>): List<Suggestion> {
    val purged = words
            .filter { e -> !stopWords.contains(e) }
            .filter { e -> e.matches("\\w+".toRegex()) }
    val maxWords2 = if (maxWords > purged.size) purged.size else maxWords
    println("----- maxWords = " + maxWords2)
    println("----- purged.size = " + purged.size)
    println("----- purged = " + purged)

    return suggestions(maxWords2, purged)
            .map(::Suggestion)
}

private fun suggestions(maxWords: Int, words: List<String>): MutableList<String> {
    var suggestions = mutableListOf<String>()
    for (i in 0..words.size - maxWords + 1 - 1) {
        suggestions.addAll(combinations(i, words, maxWords))
    }
    return suggestions
}

private fun combinations(index: Int, words: List<String>, maxWords: Int): List<String> {
    var combinations = mutableListOf<String>()
    for (i in 0..maxWords - 1) {
        val word = words[index + i]
        val newWord = if (i == 0) word else combinations[i - 1] + " " + word
        combinations.add(newWord)
    }
    return combinations
}
