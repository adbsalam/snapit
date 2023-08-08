package uk.adbsalam.snapit.ksp.codewriter

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.FileSpec
import org.junit.runners.JUnit4

/**
 * @param previewImports if file require preview imports true else false
 * @param fileName name of file to be used
 * @param functions current symbols to be processed in current file
 * @param annotation type of annotation being processed
 *
 * @return Returns a file spec that includes following
 * All needed Imports
 * File name and Test annotations needed
 */
internal fun kFile(
    previewImports: Boolean,
    fileName: String,
    functions: Sequence<KSFunctionDeclaration>,
    annotation: AnnotationType
): FileSpec.Builder {

    val packageName = functions.first().containingFile?.packageName?.asString().toString()

    val file = FileSpec
        .builder(packageName, fileName)
        .addImport(JUnit4::class, "")
        .addImport("app.cash.paparazzi", "Paparazzi")
        .addImport(PAPARAZZI_PACKAGE, "captureScreenshot")
        .addImport(PAPARAZZI_PACKAGE, paparazziInstanceImport(annotation))

    if (previewImports) {
        file
            .addImport("androidx.compose.runtime", "CompositionLocalProvider")
            .addImport("androidx.compose.ui.platform", "LocalInspectionMode")
    }
    return file
}

/**
 * @param annotation type of annotation to be processed
 * This is to initiate paparazzi instance such as
 * val paparazzi = Paparazzi.forComponent()
 * val paparazzi = Paparazzi.forScreen()
 * based on what annotation is being used
 */
private fun paparazziInstanceImport(
    annotation: AnnotationType
): String {
    return when (annotation) {
        AnnotationType.LIGHT_SCREEN -> "forScreen"
        AnnotationType.LIGHT_COMPONENT -> "forComponent"
        AnnotationType.DARK_SCREEN -> "forDarkScreen"
        AnnotationType.DARK_COMPONENT -> "forDarkComponent"
        AnnotationType.NONE -> ""
    }
}
