package uk.adbsalam.snapit.ksp

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import uk.adbsalam.snapit.ksp.FunctionProcessor

class FunctionProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return FunctionProcessor(
            logger = environment.logger,
            codeGenerator = environment.codeGenerator
        )
    }
}