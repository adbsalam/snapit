package uk.adbsalam.snapit

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.ksp.codewriter.AnnotationType
import uk.adbsalam.snapit.ksp.codewriter.kFile
import uk.adbsalam.snapit.utils.KPTest
import uk.adbsalam.snapit.utils.MockType
import uk.adbsalam.snapit.utils.mockFunctions

@RunWith(JUnit4::class)
class KPFileTest : KPTest("kfile_test_case") {

    @Test
    fun `kFile - when component annotation - and no preview imports - should create correct file`() {

        val file = kFile(
            previewImports = false,
            functions = mockFunctions(MockType.RANDOM),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.COMPONENT
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_component_no_preview_imports")

        Assert.assertEquals(file, actual)
    }

    @Test
    fun `kFile - when component annotation - and preview imports - should create correct file`() {

        val file = kFile(
            previewImports = true,
            functions = mockFunctions(MockType.RANDOM),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.COMPONENT
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_component_preview_imports")

        Assert.assertEquals(file, actual)
    }

    @Test
    fun `kFile - when screen annotation - and no preview imports - should create correct file`() {

        val file = kFile(
            previewImports = false,
            functions = mockFunctions(MockType.RANDOM),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.SCREEN
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_screen_no_preview_imports")

        Assert.assertEquals(file, actual)
    }

    @Test
    fun `kFile - when screen annotation - and preview imports - should create correct file`() {

        val file = kFile(
            previewImports = true,
            functions = mockFunctions(MockType.RANDOM),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.SCREEN
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_screen_preview_imports")

        Assert.assertEquals(file, actual)
    }

}