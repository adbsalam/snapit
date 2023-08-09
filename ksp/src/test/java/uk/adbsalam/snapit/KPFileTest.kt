package uk.adbsalam.snapit

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.ksp.codewriter.AnnotationType
import uk.adbsalam.snapit.ksp.codewriter.kFile
import uk.adbsalam.snapit.utils.KPTest
import uk.adbsalam.snapit.utils.MockType
import uk.adbsalam.snapit.utils.mockDarkFun
import uk.adbsalam.snapit.utils.mockFunctions
import uk.adbsalam.snapit.utils.mockSingleFunction
import uk.adbsalam.snapit.utils.mockkGifFun

@RunWith(JUnit4::class)
class KPFileTest : KPTest("kfile_test_case") {

    @Test
    fun `kFile - when component annotation - and no preview imports - should create correct file`() {

        val file = kFile(
            previewImports = false,
            functions = mockFunctions(MockType.RANDOM),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.LIGHT_COMPONENT
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
            annotation = AnnotationType.LIGHT_COMPONENT
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
            annotation = AnnotationType.LIGHT_SCREEN
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
            annotation = AnnotationType.LIGHT_SCREEN
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_screen_preview_imports")

        Assert.assertEquals(file, actual)
    }

    @Test
    fun `kFile - when screen annotation - and dark mode - should create correct file`() {

        val file = kFile(
            previewImports = false,
            functions = mockDarkFun(true),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.DARK_SCREEN
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_screen_dark_imports")

        Assert.assertEquals(file, actual)
    }

    @Test
    fun `kFile - when component annotation - and dark imports - should create correct file`() {

        val file = kFile(
            previewImports = false,
            functions = mockDarkFun(false),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.DARK_COMPONENT
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_component_dark_imports")

        Assert.assertEquals(file, actual)
    }

    @Test
    fun `kFile - when screen annotation - and dark mode and preview imports - should create correct file`() {

        val file = kFile(
            previewImports = true,
            functions = mockDarkFun(true),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.DARK_SCREEN
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_screen_dark_preview_imports")

        Assert.assertEquals(file, actual)
    }

    @Test
    fun `kFile - when component annotation - and dark imports and preview imports - should create correct file`() {

        val file = kFile(
            previewImports = true,
            functions = mockDarkFun(false),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.DARK_COMPONENT
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_component_dark_preview_imports")

        Assert.assertEquals(file, actual)
    }

    @Test
    fun `kFile - when gif annotation - and dark mode - should create correct file`() {

        val file = kFile(
            previewImports = true,
            functions = mockkGifFun(false),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.LIGHT_GIF
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_gif_light")

        Assert.assertEquals(file, actual)
    }

    @Test
    fun `kFile - when gif annotation and dark mode - should create correct file`() {

        val file = kFile(
            previewImports = true,
            functions = mockkGifFun(true),
            fileName = "TestFileComponentTest",
            annotation = AnnotationType.DARK_GIF
        ).build().toString()

        val actual = kspCodeFromFile("ks_file_gif_dark")

        Assert.assertEquals(file, actual)
    }

}