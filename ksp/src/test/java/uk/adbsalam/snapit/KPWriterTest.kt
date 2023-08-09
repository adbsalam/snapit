package uk.adbsalam.snapit

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.ksp.codewriter.AnnotationType
import uk.adbsalam.snapit.ksp.codewriter.codeFile
import uk.adbsalam.snapit.ksp.codewriter.commentedFile
import uk.adbsalam.snapit.utils.KPTest
import uk.adbsalam.snapit.utils.MockType
import uk.adbsalam.snapit.utils.mockDarkFun
import uk.adbsalam.snapit.utils.mockFunctions
import uk.adbsalam.snapit.utils.mockkGifFun

@RunWith(JUnit4::class)
class KPWriterTest : KPTest("kwriter_test_case") {

    @Test
    fun `codeFile - when no previews - component annotation - should generate code correctly`() {
        val functions = mockFunctions(preview = MockType.NONE_PREVIEW)
        val codeFile = codeFile(
            previewImports = false,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.LIGHT_COMPONENT
        )
        val actual = kspCodeFromFile("kwriter_components_no_preview")
        Assert.assertEquals(codeFile, actual)
    }

    @Test
    fun `codeFile - when previews - component annotation - should generate code correctly`() {
        val functions = mockFunctions(preview = MockType.RANDOM)
        val codeFile = codeFile(
            previewImports = true,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.LIGHT_COMPONENT
        )
        val actual = kspCodeFromFile("kwriter_components_with_preview")
        Assert.assertEquals(codeFile, actual)
    }

    @Test
    fun `codeFile - when no previews - screen annotation - should generate code correctly`() {
        val functions = mockFunctions(preview = MockType.NONE_PREVIEW)
        val codeFile = codeFile(
            previewImports = false,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.LIGHT_SCREEN
        )
        val actual = kspCodeFromFile("kwriter_screen_no_preview")
        Assert.assertEquals(codeFile, actual)
    }

    @Test
    fun `codeFile - when previews - screen annotation - should generate code correctly`() {
        val functions = mockFunctions(preview = MockType.RANDOM)
        val codeFile = codeFile(
            previewImports = true,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.LIGHT_SCREEN
        )
        val actual = kspCodeFromFile("kwriter_screen_preview")
        Assert.assertEquals(codeFile, actual)
    }

    @Test
    fun `commentedFile - when file generated - should be commented and cleaned correctly`() {
        val functions = mockFunctions(preview = MockType.RANDOM)
        val codeFile = codeFile(
            previewImports = true,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.LIGHT_SCREEN
        )
        val commented = commentedFile(codeFile)
        val actual = kspCodeFromFile("kwriter_commented_clean_file")

        Assert.assertEquals(commented, actual)
    }

    @Test
    fun `commentedFile - dark components file generated- should be commented and cleaned correctly`() {
        val functions = mockDarkFun(false)
        val codeFile = codeFile(
            previewImports = true,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.DARK_COMPONENT
        )
        val commented = commentedFile(codeFile)
        val actual = kspCodeFromFile("kwriter_dark_component")

        Assert.assertEquals(commented, actual)
    }

    @Test
    fun `commentedFile - dark screen file generated- should be commented and cleaned correctly`() {
        val functions = mockDarkFun(true)
        val codeFile = codeFile(
            previewImports = false,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.DARK_SCREEN
        )
        val commented = commentedFile(codeFile)
        val actual = kspCodeFromFile("ktwriter_dark_screen")

        Assert.assertEquals(commented, actual)
    }

    @Test
    fun `commentedFile - dark gif with previews file generated- should be commented and cleaned correctly`() {
        val functions = mockkGifFun(true)
        val codeFile = codeFile(
            previewImports = true,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.DARK_GIF
        )
        val commented = commentedFile(codeFile)
        val actual = kspCodeFromFile("kwriter_dark_gif")

        Assert.assertEquals(commented, actual)
    }

    @Test
    fun `commentedFile - light screen file generated- should be commented and cleaned correctly`() {
        val functions = mockkGifFun(false)
        val codeFile = codeFile(
            previewImports = false,
            fileName = "TestExample",
            symbols = functions,
            annotation = AnnotationType.LIGHT_GIF
        )
        val commented = commentedFile(codeFile)
        val actual = kspCodeFromFile("kwriter_light_gif")

        Assert.assertEquals(commented, actual)
    }
}