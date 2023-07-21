package uk.adbsalam.snapit.utils

import com.squareup.kotlinpoet.FunSpec

/**
 *
 */
fun joinFunctions(functions: Iterable<FunSpec>): String {
    var generatedText = ""

    functions.iterator().forEachRemaining {
        generatedText += "$it\n"
    }
    return generatedText
}
