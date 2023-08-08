package uk.adbsalam.snapit

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.ksp.codewriter.AnnotationType
import uk.adbsalam.snapit.ksp.codewriter.getMethodName
import uk.adbsalam.snapit.ksp.codewriter.nonPreviewFuncSpec
import uk.adbsalam.snapit.ksp.codewriter.previewFuncSpec
import uk.adbsalam.snapit.ksp.codewriter.snapFunctions
import uk.adbsalam.snapit.utils.KPTest
import uk.adbsalam.snapit.utils.MockType
import uk.adbsalam.snapit.utils.joinFunctions
import uk.adbsalam.snapit.utils.mockDarkFun
import uk.adbsalam.snapit.utils.mockFunctions
import uk.adbsalam.snapit.utils.mockSingleFunction

@RunWith(JUnit4::class)
class KPFunctionTest : KPTest("kfunction_test_case") {

    @Test
    fun `snapFunctions - when passed preview true functions - should create a list of preview functions`() {
        val functions = mockFunctions(MockType.ALL_PREVIEW)
        val snapFunctions = snapFunctions(functions, AnnotationType.LIGHT_COMPONENT)
        val generatedFunctions = joinFunctions(snapFunctions)
        val actual = kspCodeFromFile("kfunctions_all_preview_functions")
        Assert.assertEquals(generatedFunctions, actual)
    }

    @Test
    fun `snapFunctions - when passed preview false functions - should create a list of preview functions`() {
        val functions = mockFunctions(MockType.NONE_PREVIEW)
        val snapFunctions = snapFunctions(functions, AnnotationType.LIGHT_SCREEN)
        val generatedFunctions = joinFunctions(snapFunctions)
        val actual = kspCodeFromFile("kfunctions_all_no_preview_functions")
        Assert.assertEquals(generatedFunctions, actual)
    }

    @Test
    fun `previewFuncSpec - when function with preview value set to true - should generate function correctly`() {
        val mockFunction = mockSingleFunction("when some test, should run test", "ExamplePreview")

        val previewFunction = previewFuncSpec(mockFunction, AnnotationType.LIGHT_COMPONENT).toString()
        val actual = kspCodeFromFile("kfunction_preview_function")
        Assert.assertEquals(previewFunction, actual)
    }

    @Test
    fun `previewFuncSpec - when function with preview value set to false - should generate function correctly`() {
        val mockFunction = mockSingleFunction("when some test, should run test", "ExamplePreview")

        val nonPreviewFunction = nonPreviewFuncSpec(mockFunction, AnnotationType.LIGHT_COMPONENT).toString()
        val actual = kspCodeFromFile("kfunction_no_preview_function")
        Assert.assertEquals(nonPreviewFunction, actual)
    }

    @Test
    fun `previewFuncSpec - dark screen and preview - should generate function correctly`() {
        val mockFunction = mockDarkFun(false).first()

        val previewFunction = previewFuncSpec(mockFunction, AnnotationType.DARK_COMPONENT).toString()
        val actual = kspCodeFromFile("kfunction_dark_component_preview")
        Assert.assertEquals(previewFunction, actual)
    }

    @Test
    fun `previewFuncSpec - dark screen and non preview - should generate function correctly`() {
        val mockFunction = mockDarkFun(false).first()

        val previewFunction = nonPreviewFuncSpec(mockFunction, AnnotationType.DARK_COMPONENT).toString()
        val actual = kspCodeFromFile("kfunction_dark_component_no_preview")
        Assert.assertEquals(previewFunction, actual)
    }

    @Test
    fun `getMethodName - when name param added to annotation, should generate function with correct name`() {
        val funWithAnnotationNameParamValueSet =
            mockSingleFunction(
                annotationNameValue = "when some test, should run test",
                funName = "ExamplePreview"
            )

        val funWithAnnotationNameParamValueNotSet =
            mockSingleFunction(
                annotationNameValue = "",
                funName = "ExamplePreview"
            )

        val funNameWithAnnotationParamValueSet =
            getMethodName(
                function = funWithAnnotationNameParamValueSet
            )

        val funNameWithAnnotationParamValueNotSet =
            getMethodName(
                function = funWithAnnotationNameParamValueNotSet
            )

        Assert.assertEquals(funNameWithAnnotationParamValueSet, "when some test, should run test")
        Assert.assertEquals(funNameWithAnnotationParamValueNotSet, "examplePreviewSnapTest")
    }

}