package uk.adbsalam.snapit

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
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
    fun `isScreenComponent, when function have isScreen set to true, should return true`(){
        val screenFun = mockSingleFunction(
            annotationNameValue = "when in test, run test",
            funName = "PreviewTest",
            isPreview = false,
            isScreen = true
        )

        val result = isScreenComponent(screenFun)
        Assert.assertEquals(result, true)
    }

    @Test
    fun `isScreenComponent, when function have isScreen set to false, should return true`(){
        val screenFun = mockSingleFunction(
            annotationNameValue = "when in test, run test",
            funName = "PreviewTest",
            isPreview = false,
            isScreen = false
        )

        val result = isScreenComponent(screenFun)
        Assert.assertEquals(result, false)
    }


}