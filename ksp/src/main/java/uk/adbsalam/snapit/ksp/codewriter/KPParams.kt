package uk.adbsalam.snapit.ksp.codewriter

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument
import uk.adbsalam.snapit.annotations.SnapAnnotation.SNAP_IT

/**
 * @param function function to be processed
 * @return True if function annotation contains preview = true
 * to allow the writer know this functions needs to be processed as a preview function
 */
internal fun requirePreviewContext(
    function: KSFunctionDeclaration,
): Boolean {
    val annotation: KSAnnotation = function.annotations.first {
        it.shortName.asString() == SNAP_IT.annotation
    }

    val tagArg: KSValueArgument =
        annotation.arguments.first { arg -> arg.name?.asString() == "preview" }

    return tagArg.value as Boolean
}

/**
 * @param function function to be processed
 * @return True if function annotation contains isScreen = true
 * This is to allow writer know generated code needs to have screen paparazzi instance
 **/
internal fun componentType(
    function: KSFunctionDeclaration,
): AnnotationType {
    val annotation: KSAnnotation = function.annotations.first {
        it.shortName.asString() == SNAP_IT.annotation
    }

    val screenArg: KSValueArgument =
        annotation.arguments.first { arg -> arg.name?.asString() == "isScreen" }

    val dark: KSValueArgument =
        annotation.arguments.first { arg -> arg.name?.asString() == "isDark" }

    val gif: KSValueArgument =
        annotation.arguments.first { arg -> arg.name?.asString() == "gif" }

    val isScreen = screenArg.value as Boolean
    val isDark = dark.value as Boolean
    val isGif = gif.value as Boolean

    return when {
        isGif && !isDark -> AnnotationType.LIGHT_GIF
        isGif && isDark -> AnnotationType.DARK_GIF
        !isScreen && !isDark -> AnnotationType.LIGHT_COMPONENT
        isScreen && !isDark -> AnnotationType.LIGHT_SCREEN
        isScreen && isDark -> AnnotationType.DARK_SCREEN
        else -> AnnotationType.DARK_COMPONENT
    }
}