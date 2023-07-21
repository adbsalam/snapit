package uk.adbsalam.snapit

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.ksp.FunctionProcessor
import uk.adbsalam.snapit.ksp.FunctionProcessorProvider

@RunWith(JUnit4::class)
class ProcessorProviderTest {

    @Test
    fun `FunctionProcessorProvider - when initialised - creates processor correctly`() {
        val symbolProcessorProvider = FunctionProcessorProvider()

        val env = mockk<SymbolProcessorEnvironment>(relaxed = true)

        val provider = symbolProcessorProvider.create(env)

        Assert.assertTrue(provider is FunctionProcessor)
    }

}