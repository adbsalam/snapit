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

    val testPackageName = "uk.co.test.name"

    val ksPackageName = Mockito.mock(KSName::class.java).stub {
        on { getShortName() } doReturn testPackageName
    }

    val ksFile = Mockito.mock(KSFile::class.java).stub {
        on { packageName } doReturn ksPackageName
        on { packageName.asString() } doReturn testPackageName
    }

    val nameParam = Mockito.mock(KSName::class.java).stub {
        on { getShortName() } doReturn "name"
    }

    val previewParam = Mockito.mock(KSName::class.java).stub {
        on { getShortName() } doReturn "preview"
    }

    val screenParam = Mockito.mock(KSName::class.java).stub {
        on { getShortName() } doReturn "isScreen"
    }

    val annotationName = Mockito.mock(KSName::class.java).stub {
        on { getShortName() } doReturn "SnapIt"
    }

    val ksNameArgument = Mockito.mock(KSValueArgument::class.java).stub {
        on { name } doReturn nameParam
        on { name?.asString() } doReturn "name"
        on { value } doReturn annotationNameValue
    }

    val ksPreviewArgument = Mockito.mock(KSValueArgument::class.java).stub {
        on { name } doReturn previewParam
        on { name?.asString() } doReturn "preview"
        on { value } doReturn isPreview
    }

    val ksScreenArgument = Mockito.mock(KSValueArgument::class.java).stub {
        on { name } doReturn screenParam
        on { name?.asString() } doReturn "isScreen"
        on { value } doReturn isScreen
    }

    val ksAnnotation = Mockito.mock(KSAnnotation::class.java).stub {
        on { shortName } doReturn annotationName
        on { shortName.asString() } doReturn "SnapIt"
        on { arguments } doReturn listOf(ksNameArgument, ksPreviewArgument, ksScreenArgument)
    }

    val ksFunctionMock = Mockito.mock(KSFunctionDeclaration::class.java).stub {
        on { annotations } doReturn sequenceOf(ksAnnotation)
        on { toString() } doReturn funName
        on { containingFile } doReturn ksFile
    }

    return ksFunctionMock
}