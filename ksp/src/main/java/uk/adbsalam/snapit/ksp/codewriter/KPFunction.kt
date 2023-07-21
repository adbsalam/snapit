package uk.adbsalam.snapit.ksp.codewriter

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument
import com.squareup.kotlinpoet.FunSpec
import org.junit.Test
import uk.adbsalam.snapit.annotations.SnapAnnotation

/**
 * @param functions functions to be processes
 *
 * @return list of functions to be added to file,
 * processes and generates functions based on param preview
 * if preview = true then generate function with local inspection mode true
 * else create a basic snap test function
 */
internal fun snapFunctions(
    functions: Sequence<KSFunctionDeclaration>
): Iterable<FunSpec> {
    return functions.map { kFun ->
        if (requirePreviewContext(kFun)) {
            previewFuncSpec(kFun)
        } else {
            nonPreviewFuncSpec(kFun)
        }
    }.asIterable()
}

/**
 * @param function function to be processed
 * @return returns a function with capture screenshot commands
 */
internal fun nonPreviewFuncSpec(
    function: KSFunctionDeclaration
): FunSpec {
    return FunSpec.builder(getMethodName(function))
        .addAnnotation(Test::class)
        .addCode(
            """
                |paparazzi.captureScreenshot {
                |    $function()
                |}
                """.trimMargin()
        )
        .build()
}

/**
 * @param function function to be processed
 * @return returns a function with capture screenshot commands
 * and set inspection mode true command
 */
internal fun previewFuncSpec(
    function: KSFunctionDeclaration
): FunSpec {
    return FunSpec.builder(getMethodName(function))
        .addAnnotation(Test::class)
        .addCode(
            """
                |paparazzi.captureScreenshot {
                |    CompositionLocalProvider(LocalInspectionMode provides true) {
                |        $function()
                |    }
                |}
                """.trimMargin()
        )
        .build()
}

/**
 * @param function to be processed
 * @return Function name based on param value of name: String
 * if a name:String value exists then use it as function name
 * if no name:String value exists then use current function name and add SnapTest at end
 * example "Greetings" function will generate greetingsSnapTest() function name
 */
fun getMethodName(
    function: KSFunctionDeclaration
): String {
    val annotation: KSAnnotation = function.annotations.first {
        it.shortName.asString() == SnapAnnotation.SNAP_IT.annotation
    }

    val tagArg: KSValueArgument =
        annotation.arguments.first { arg -> arg.name?.asString() == "name" }

    val value = tagArg.value as String

    if (value.isNotEmpty()) {
        return value
    }

    val functionName = function.toString().replaceFirst(
        oldChar = function.toString().first(),
        newChar = function.toString().first().lowercaseChar()
    )

    return value.ifEmpty { "${functionName}SnapTest" }
}