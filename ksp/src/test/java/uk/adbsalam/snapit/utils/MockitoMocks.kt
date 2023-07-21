package uk.adbsalam.snapit.utils

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSValueArgument
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub

enum class MockType {
    ALL_PREVIEW, NONE_PREVIEW, RANDOM
}

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

internal fun mockSingleFunction(
    annotationNameValue: String = "",
    funName: String,
    isPreview: Boolean = false,
    isScreen: Boolean = false
): KSFunctionDeclaration {
    return annotatedFunction(
        annotationNameValue,
        funName,
        isPreview,
        isScreen
    )
}

/**
 *
 */
private fun annotatedFunction(
    annotationNameValue: String,
    funName: String,
    isPreview: Boolean = false,
    isScreen: Boolean = false
): KSFunctionDeclaration {

    val file = mockFile()

    val annotation = mockSnapAnnotation(
        paramName = annotationNameValue,
        isPreview = isPreview,
        isScreen = isScreen
    )

    val ksFunctionMock = Mockito.mock(KSFunctionDeclaration::class.java).stub {
        on { annotations } doReturn sequenceOf(annotation)
        on { toString() } doReturn funName
        on { containingFile } doReturn file
    }

    return ksFunctionMock
}


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

private fun mockSnapAnnotation(
    paramName: String,
    isPreview: Boolean = false,
    isScreen: Boolean = false
): KSAnnotation {

    val annotationName = Mockito.mock(KSName::class.java).stub {
        on { getShortName() } doReturn "SnapIt"
    }

    val nameArg = mockArg("name", paramName)
    val previewArg = mockArg("preview", isPreview)
    val isScreenArg = mockArg("isScreen", isScreen)

    return Mockito.mock(KSAnnotation::class.java).stub {
        on { shortName } doReturn annotationName
        on { shortName.asString() } doReturn "SnapIt"
        on { arguments } doReturn listOf(nameArg, previewArg, isScreenArg)
    }

}
