package uk.adbsalam.snapit.utils

import com.squareup.kotlinpoet.FunSpec

/**
 * @param functions
 *
 * This is to combine all functions into one big test
 * SO it can be tested as a file from resources
 */
fun joinFunctions(functions: Iterable<FunSpec>): String {
    var generatedText = ""

    functions.iterator().forEachRemaining {
        generatedText += "$it\n"
    }
    return generatedText
}
