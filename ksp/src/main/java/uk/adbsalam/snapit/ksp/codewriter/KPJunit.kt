package uk.adbsalam.snapit.ksp.codewriter

import app.cash.paparazzi.Paparazzi
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * @param fileName file name currently being used to name class same as file name
 * @param symbols symbols to be processes to add functions for each
 * @param annotation current annotation type if test is forComponent or forScreen
 *
 * @return returns a JUnit4 Class with based on annotation type.
 * If annotation is Screen then create and import instance of paparazzi.forScreen()
 * else create instance of paparazzi.forComponent
 */
internal fun jUnitClass(
    fileName: String,
    symbols: Sequence<KSFunctionDeclaration>,
    annotation: AnnotationType
): TypeSpec {

    val snapFunctions = snapFunctions(symbols)

    val runWith =
        AnnotationSpec.builder(RunWith::class).addMember("JUnit4::class").build()

    val ruleAnnotation = AnnotationSpec.builder(Rule::class).useSiteTarget(
        AnnotationSpec.UseSiteTarget.GET
    ).build()

    val codeBlock = CodeBlock.builder().add(paparazziInitializer(annotation)).build()

    val testRule = PropertySpec
        .builder("paparazzi", Paparazzi::class.java)
        .addAnnotation(ruleAnnotation)
        .initializer(codeBlock)
        .build()

    return TypeSpec.classBuilder(fileName)
        .addAnnotation(runWith)
        .addProperty(testRule)
        .addFunctions(snapFunctions)
        .build()
}

/**
 * @param annotation annotation to be processes
 * return correct instance of paparazzi to be used for either screen or component
 */
private fun paparazziInitializer(
    annotation: AnnotationType
): String {
    return if (annotation == AnnotationType.COMPONENT)
        "Paparazzi.forComponent()"
    else
        "Paparazzi.forScreen()"
}

