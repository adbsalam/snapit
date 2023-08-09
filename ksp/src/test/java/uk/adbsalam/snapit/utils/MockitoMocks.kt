package uk.adbsalam.snapit.utils

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSValueArgument
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub

internal enum class MockType {
    ALL_PREVIEW, NONE_PREVIEW, RANDOM
}

/**
 * @param annotationNameValue annotation param "name" value to be set
 * @param funName function name to be set
 * @param isPreview is preview param to be set to true or false
 * @param isScreen isScreen param to be set to true or false
 *
 * This will help generate a single function for testing purposes
 */
internal fun mockSingleFunction(
    annotationNameValue: String = "",
    funName: String,
    isPreview: Boolean = false,
    isScreen: Boolean = false,
    isDark: Boolean = false,
    isGif: Boolean = false
): KSFunctionDeclaration {
    return annotatedFunction(
        annotationNameValue = annotationNameValue,
        funName = funName,
        isPreview = isPreview,
        isScreen = isScreen,
        isDark = isDark,
        isGif = isGif
    )
}

/**
 * @param preview Mock type to generate code as
 * if ALL_PREVIEW - functions are to be set with preview true
 * if NONE_PREVIEW - functions are to be set with preview false
 * if RANDOM - functions will be generates as random preview, in an order
 *
 * Ths will provide list of functions for testing of code generation and kotlin poet
 */
internal fun mockFunctions(
    preview: MockType,
): Sequence<KSFunctionDeclaration> {

    if (preview == MockType.RANDOM) {
        return sequenceOf(
            annotatedFunction("", "ExamplePreviewOne", true),
            annotatedFunction("", "ExamplePreviewTwo", false),
            annotatedFunction("", "ExamplePreviewThree", true),
        )
    }

    val isPreview = preview == MockType.ALL_PREVIEW

    return sequenceOf(
        annotatedFunction("", "ExamplePreviewOne", isPreview),
        annotatedFunction("", "ExamplePreviewTwo", isPreview),
        annotatedFunction("", "ExamplePreviewThree", isPreview),
    )
}

internal fun mockDarkFun(isScreen: Boolean) = sequenceOf(
    annotatedFunction(
        annotationNameValue = "when dark, should render",
        funName = "darkFunTest",
        isScreen = isScreen,
        isDark = true
    ),
    annotatedFunction(
        annotationNameValue = "when dark, should render",
        funName = "darkFunTest",
        isScreen = isScreen,
        isDark = true
    )
)

internal fun mockkGifFun(isDark: Boolean) = sequenceOf(
    annotatedFunction(
        annotationNameValue = "when dark, should render",
        funName = "darkFunTest",
        isDark = isDark,
        isGif = true
    ),
    annotatedFunction(
        annotationNameValue = "when dark, should render",
        funName = "darkFunTest",
        isDark = isDark,
        isGif = true
    )
)

/**
 * @param annotationNameValue annotation param "name" value to be set
 * @param funName function name to be set
 * @param isPreview is preview param to be set to true or false
 * @param isScreen isScreen param to be set to true or false
 *
 * This will help generate a single function for testing purposes
 */
private fun annotatedFunction(
    annotationNameValue: String,
    funName: String,
    isPreview: Boolean = false,
    isScreen: Boolean = false,
    isDark: Boolean = false,
    isGif: Boolean = false
): KSFunctionDeclaration {

    val file = mockFile()

    val annotation = mockSnapAnnotation(
        paramName = annotationNameValue,
        isPreview = isPreview,
        isScreen = isScreen,
        isDark = isDark,
        isGif = isGif
    )

    val ksFunctionMock = Mockito.mock(KSFunctionDeclaration::class.java).stub {
        on { annotations } doReturn sequenceOf(annotation)
        on { toString() } doReturn funName
        on { containingFile } doReturn file
    }

    return ksFunctionMock
}

/**
 * Create a mock file with test package name
 *
 * This is to be used by functions to provide its containing class and package names
 * This is to be used by kotlin poet for naming purposes
 */
private fun mockFile(): KSFile {
    val testPackageName = "uk.co.test.name"

    val ksPackageName = Mockito.mock(KSName::class.java).stub {
        on { getShortName() } doReturn testPackageName
    }

    return Mockito.mock(KSFile::class.java).stub {
        on { packageName } doReturn ksPackageName
        on { packageName.asString() } doReturn testPackageName
    }
}

/**
 * @param stringName name of param as string
 * @param paramValue value of Type<T> to be set for annotation param
 *
 * this will set argument name and value to be used in annotation
 */
private fun <T> mockArg(
    stringName: String,
    paramValue: T
): KSValueArgument {

    val ksName = Mockito.mock(KSName::class.java).stub {
        on { getShortName() } doReturn stringName
    }
    return Mockito.mock(KSValueArgument::class.java).stub {
        on { name } doReturn ksName
        on { name?.asString() } doReturn stringName
        on { value } doReturn paramValue
    }
}

/**
 * @param paramName function name to be set
 * @param isPreview is preview param to be set to true or false
 * @param isScreen isScreen param to be set to true or false
 * @param isDark isDark param to be set to true or false
 *
 * this will generate mock of annotation, along with its params to be tested
 */
private fun mockSnapAnnotation(
    paramName: String,
    isPreview: Boolean = false,
    isScreen: Boolean = false,
    isDark: Boolean = false,
    isGif: Boolean = false
): KSAnnotation {

    val annotationName = Mockito.mock(KSName::class.java).stub {
        on { getShortName() } doReturn "SnapIt"
    }

    val nameArg = mockArg("name", paramName)
    val previewArg = mockArg("preview", isPreview)
    val isScreenArg = mockArg("isScreen", isScreen)
    val isDarkArg = mockArg("isDark", isDark)
    val gifArg = mockArg("gif", isGif)
    val startArg = mockArg("start", 0L)
    val endArg = mockArg("end", 500L)
    val fpsArg = mockArg("fps", 30)

    return Mockito.mock(KSAnnotation::class.java).stub {
        on { shortName } doReturn annotationName
        on { shortName.asString() } doReturn "SnapIt"
        on { arguments } doReturn listOf(nameArg, previewArg, isScreenArg, isDarkArg, gifArg, startArg, endArg, fpsArg)
    }

}
