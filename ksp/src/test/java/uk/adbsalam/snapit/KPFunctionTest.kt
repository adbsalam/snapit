package uk.adbsalam.snapit

import com.squareup.kotlinpoet.FunSpec
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.ksp.codewriter.getMethodName
import uk.adbsalam.snapit.ksp.codewriter.nonPreviewFuncSpec
import uk.adbsalam.snapit.ksp.codewriter.previewFuncSpec
import uk.adbsalam.snapit.ksp.codewriter.snapFunctions
import uk.adbsalam.snapit.utils.KPTest
import uk.adbsalam.snapit.utils.joinFunctions

@RunWith(JUnit4::class)
class KPFunctionTest : KPTest("kfunction_test_case") {

    @Test
    fun `snapFunctions - when passed preview true functions - should create a list of preview functions`() {
        val functions = sequenceOf(
            annotatedFunction("", "ExamplePreviewOne", true),
            annotatedFunction("", "ExamplePreviewTwo", true),
            annotatedFunction("", "ExamplePreviewThree", true),
        )
        val snapFunctions = snapFunctions(functions)
        var generatedFunctions = joinFunctions(snapFunctions)
        val actual = kspCodeFromFile("kfunctions_all_preview_functions")
        Assert.assertEquals(generatedFunctions, actual)
    }

    @Test
    fun `snapFunctions - when passed preview false functions - should create a list of preview functions`() {
        val functions = sequenceOf(
            annotatedFunction("", "ExamplePreviewOne", false),
            annotatedFunction("", "ExamplePreviewTwo", false),
            annotatedFunction("", "ExamplePreviewThree", false),
        )
        val snapFunctions = snapFunctions(functions)
        var generatedFunctions = joinFunctions(snapFunctions)
        val actual = kspCodeFromFile("kfunctions_all_no_preview_functions")
        Assert.assertEquals(generatedFunctions, actual)
    }

    @Test
    fun `previewFuncSpec - when function with preview value set to true - should generate function correctly`() {
        val mockFunction = annotatedFunction("when some test, should run test", "ExamplePreview")

        val previewFunction = previewFuncSpec(mockFunction).toString()
        val actual = kspCodeFromFile("kfunction_preview_function")
        Assert.assertEquals(previewFunction, actual)
    }

    @Test
    fun `previewFuncSpec - when function with preview value set to false - should generate function correctly`() {
        val mockFunction = annotatedFunction("when some test, should run test", "ExamplePreview")

        val nonPreviewFunction = nonPreviewFuncSpec(mockFunction).toString()
        val actual = kspCodeFromFile("kfunction_no_preview_function")
        Assert.assertEquals(nonPreviewFunction, actual)
    }

    @Test
    fun `getMethodName - when name param added to annotation, should generate function with correct name`() {
        val funWithAnnotationNameParamValueSet =
            annotatedFunction(
                annotationNameValue = "when some test, should run test",
                funName = "ExamplePreview"
            )

        val funWithAnnotationNameParamValueNotSet =
            annotatedFunction(
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