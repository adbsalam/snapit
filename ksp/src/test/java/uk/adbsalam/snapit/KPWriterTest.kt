package uk.adbsalam.snapit

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.ksp.codewriter.AnnotationType
import uk.adbsalam.snapit.ksp.codewriter.codeFile
import uk.adbsalam.snapit.utils.KPTest
import uk.adbsalam.snapit.utils.MockType
import uk.adbsalam.snapit.utils.mockFunctions

@RunWith(JUnit4::class)
class KPWriterTest: KPTest("kwriter_test_case") {

    @Test
    fun `codeFile - when no previews - component annotation - should generate code correctly`(){
        val functions = mockFunctions(preview = MockType.NONE_PREVIEW)
        val codeFile = codeFile(
            previewImports = false,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.COMPONENT
        )
        val actual = kspCodeFromFile("kwriter_components_no_preview")
        Assert.assertEquals(codeFile, actual)
    }

    @Test
    fun `codeFile - when previews - component annotation - should generate code correctly`(){
        val functions = mockFunctions(preview = MockType.RANDOM)
        val codeFile = codeFile(
            previewImports = true,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.COMPONENT
        )
        val actual = kspCodeFromFile("kwriter_components_with_preview")
        Assert.assertEquals(codeFile, actual)
    }

    @Test
    fun `codeFile - when no previews - screen annotation - should generate code correctly`(){
        val functions = mockFunctions(preview = MockType.NONE_PREVIEW)
        val codeFile = codeFile(
            previewImports = false,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.SCREEN
        )
        val actual = kspCodeFromFile("kwriter_screen_no_preview")
        Assert.assertEquals(codeFile, actual)
    }

    @Test
    fun `codeFile - when previews - screen annotation - should generate code correctly`(){
        val functions = mockFunctions(preview = MockType.RANDOM)
        val codeFile = codeFile(
            previewImports = true,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.SCREEN
        )
        val actual = kspCodeFromFile("kwriter_screen_preview")
        Assert.assertEquals(codeFile, actual)
    }

}