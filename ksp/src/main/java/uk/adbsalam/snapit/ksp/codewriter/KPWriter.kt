package uk.adbsalam.snapit.ksp.codewriter

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.FileSpec
import java.io.OutputStream

/**
 * @param codeGenerator code generator instance from processor to create new files
 * @param resolver resolver instance to be passed
 * @param symbols symbols that needs to be processed
 * @param annotation current annotation type being used, i.e Screen or Component
 *
 * This function acts as entry point to code generation
 * filters symbols based on correct params, and annotations
 * generated, File, JUnit4 class and functions needed
 * Write file to generated folder and close stream
 */
fun processSymbols(
    codeGenerator: CodeGenerator,
    resolver: Resolver,
    symbols: Sequence<KSFunctionDeclaration>,
    annotation: AnnotationType
) {

    val fileNames = symbols.map {
        it.containingFile?.fileName ?: UNKNOWN_FILE
    }.toSet()

    fileNames.forEach { currentFile ->
        val fileName = getFileName(currentFile, annotation)
        val currentFileSymbols = symbols.filter { it.containingFile?.fileName == currentFile }
        val previewImports = currentFileSymbols.any { requirePreviewContext(it) }

        val file = createGeneratorFile(
            fileName = fileName,
            codeGenerator = codeGenerator,
            resolver = resolver
        )

        val codeFile = codeFile(
            previewImports = previewImports,
            fileName = fileName,
            symbols = currentFileSymbols,
            annotation = annotation
        )

        val commentedFile = commentedFile(codeFile)
        file += commentedFile
        file.close()
    }
}

/**
 * @param previewImports are preview context imports required
 * @param fileName name of file to be used
 * @param symbols current symbols to be processed
 * @param annotation current annotation to be processed
 *
 * This function will generate the code file for given symbols,
 * process includes creating a new .kt file
 * then write a JUnit4 test class
 * generate test functions for each symbol
 *
 * @return returns the new generated file
 */
internal fun codeFile(
    previewImports: Boolean,
    fileName: String,
    symbols: Sequence<KSFunctionDeclaration>,
    annotation: AnnotationType
): String {

    val kPoetFile = kFile(
        previewImports = previewImports,
        fileName = fileName,
        functions = symbols,
        annotation = annotation
    )

    val junitClass = jUnitClass(
        fileName = fileName,
        symbols = symbols,
        annotation = annotation
    )

    return kPoetFile
        .addType(junitClass)
        .build()
        .toString()
}

/**
 * @param generatedCode generated code for file as String
 * @return returns a commented file that can be used in generated files
 *
 * Reason for commented file is that paparazzi or test implementations will not work in generated folders
 * to get pass this stage we generate a commented file that is then moved to test package and uncommented
 * in this way generate build code folder will not cause build errors
 *
 * @return returns generated code in commented form
 */
private fun commentedFile(
    generatedCode: String
): String {
    return FileSpec
        .builder("none", "")
        .addFileComment(generatedCode)
        .build()
        .toString()
        .replaceExtras()
}

/**
 * @param fileName name of file to be used
 * @param codeGenerator instance of code generator
 * @param resolver resolver context to be used
 *
 * @return this function returns a new generated file
 */
private fun createGeneratorFile(
    fileName: String,
    codeGenerator: CodeGenerator,
    resolver: Resolver
): OutputStream {
    return codeGenerator.createNewFile(
        dependencies = Dependencies(
            aggregating = false,
            sources = resolver.getAllFiles().toList().toTypedArray()
        ),
        packageName = PACKAGE_NAME,
        fileName = fileName
    )
}

