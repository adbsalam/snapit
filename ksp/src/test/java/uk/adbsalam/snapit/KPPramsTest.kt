package uk.adbsalam.snapit

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.ksp.codewriter.AnnotationType
import uk.adbsalam.snapit.ksp.codewriter.isScreenComponent
import uk.adbsalam.snapit.ksp.codewriter.requirePreviewContext
import uk.adbsalam.snapit.utils.mockSingleFunction

@RunWith(JUnit4::class)
class KPPramsTest {

    @Test
    fun `requirePreviewContext, when function have preview set to true, should return true`(){
        val previewFun = mockSingleFunction(
            annotationNameValue = "when in test, run test",
            funName = "PreviewTest",
            isPreview = true,
            isScreen = false
        )

        val result = requirePreviewContext(previewFun)
        Assert.assertEquals(result, true)
    }

    @Test
    fun `requirePreviewContext, when function have preview set to false, should return true`(){
        val previewFun = mockSingleFunction(
            annotationNameValue = "when in test, run test",
            funName = "PreviewTest",
            isPreview = false,
            isScreen = false
        )

        val result = requirePreviewContext(previewFun)
        Assert.assertEquals(result, false)
    }

    @Test
    fun `isScreenComponent, when function have isScreen set to true, should return LIGHT_SCREEN`(){
        val screenFun = mockSingleFunction(
            annotationNameValue = "when in test, run test",
            funName = "PreviewTest",
            isPreview = false,
            isScreen = true
        )

        val result = isScreenComponent(screenFun)
        Assert.assertEquals(result, AnnotationType.LIGHT_SCREEN)
    }

    @Test
    fun `isScreenComponent, when function have isScreen set to false, should return LIGHT_COMPONENT`(){
        val screenFun = mockSingleFunction(
            annotationNameValue = "when in test, run test",
            funName = "PreviewTest",
            isPreview = false,
            isScreen = false
        )

        val result = isScreenComponent(screenFun)
        Assert.assertEquals(result, AnnotationType.LIGHT_COMPONENT)
    }

    @Test
    fun `isScreenComponent, when function have isScreen set to true and is Dark is true, should return DARK_SCREEN`(){
        val screenFun = mockSingleFunction(
            annotationNameValue = "when in test, run test",
            funName = "PreviewTest",
            isPreview = false,
            isScreen = true,
            isDark = true
        )

        val result = isScreenComponent(screenFun)
        Assert.assertEquals(result, AnnotationType.DARK_SCREEN)
    }

    @Test
    fun `isScreenComponent, when function have isScreen set to false and isDark true, should return DARK_COMPONENT`(){
        val screenFun = mockSingleFunction(
            annotationNameValue = "when in test, run test",
            funName = "PreviewTest",
            isPreview = false,
            isScreen = false,
            isDark = true
        )

        val result = isScreenComponent(screenFun)
        Assert.assertEquals(result, AnnotationType.DARK_COMPONENT)
    }

}