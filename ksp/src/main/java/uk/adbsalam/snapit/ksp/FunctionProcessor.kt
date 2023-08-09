package uk.adbsalam.snapit.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import uk.adbsalam.snapit.annotations.SnapAnnotation
import uk.adbsalam.snapit.ksp.codewriter.AnnotationType
import uk.adbsalam.snapit.ksp.codewriter.componentType
import uk.adbsalam.snapit.ksp.codewriter.processSymbols

/**
 * @param logger to present logs from processor
 * @param codeGenerator code generator context to create new files
 *
 */
class FunctionProcessor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {

    /**
     * Process annotations and create new code files
     * If no annotations exist then return empty array
     * Filter through symbols and separate out functions based on preview: param
     * Processed preview and non preview functions separately
     */
    override fun process(
        resolver: Resolver
    ): List<KSAnnotated> {

        val symbols = resolver
            .getSymbolsWithAnnotation(SnapAnnotation.SNAP_IT.id)
            .filterIsInstance<KSFunctionDeclaration>()

        if (!symbols.iterator().hasNext()) {
            logger.info("No Symbols to be processed")
            return emptyList()
        }

        AnnotationType.values().forEach {
            if (it != AnnotationType.NONE) {
                processAnnotationType(
                    symbols = symbols,
                    resolver = resolver,
                    annotationType = it
                )
            }
        }

        return symbols.filterNot { it.validate() }.toList()
    }

    private fun processAnnotationType(
        symbols: Sequence<KSFunctionDeclaration>,
        resolver: Resolver,
        annotationType: AnnotationType
    ): Sequence<KSFunctionDeclaration> {

        val screenSymbols = symbols.filter { componentType(it) == annotationType }

        if (screenSymbols.iterator().hasNext()) {
            processSymbols(
                symbols = screenSymbols,
                codeGenerator = codeGenerator,
                resolver = resolver,
                annotation = annotationType
            )
        }
        return screenSymbols
    }
}

