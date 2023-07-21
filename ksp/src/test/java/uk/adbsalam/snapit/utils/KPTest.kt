package uk.adbsalam.snapit.utils

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSValueArgument
import org.intellij.lang.annotations.Language
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub

open class KPTest(private val caseDir: String = "") {

    private val testPackageName = "uk.co.test.name"

    /**
     *
     */
    val mockKsFunctionDeclarations = sequenceOf(
        mockkFunctionDeclaration(),
        mockkFunctionDeclaration()
    )

    /**
     *
     */
    internal fun kspCodeFromFile(file: String): String {
        @Language("kotlin")
        val fileContent = KPTest::class.java.getResource("/$caseDir/$file.txt")?.readText()
        return fileContent.orEmpty()
    }

    /**
     *
     */
    internal fun mockkFunctionDeclaration(): KSFunctionDeclaration {
        val ksName = Mockito.mock(KSName::class.java).stub {
            on { getShortName() } doReturn testPackageName
        }

        val ksFile = Mockito.mock(KSFile::class.java).stub {
            on { packageName } doReturn ksName
            on { packageName.asString() } doReturn testPackageName
        }

        val ksFunctionMock = Mockito.mock(KSFunctionDeclaration::class.java).stub {
            on { containingFile } doReturn ksFile
        }
        return ksFunctionMock
    }

    /**
     *
     */
    fun annotatedFunction(
        annotationNameValue: String,
        funName: String,
        isPreview: Boolean = false
    ): KSFunctionDeclaration {

        val nameParam = Mockito.mock(KSName::class.java).stub {
            on { getShortName() } doReturn "name"
        }

        val previewParam = Mockito.mock(KSName::class.java).stub {
            on { getShortName() } doReturn "preview"
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

        val ksAnnotation = Mockito.mock(KSAnnotation::class.java).stub {
            on { shortName } doReturn annotationName
            on { shortName.asString() } doReturn "SnapIt"
            on { arguments } doReturn listOf(ksNameArgument, ksPreviewArgument)
        }

        val ksFunctionMock = Mockito.mock(KSFunctionDeclaration::class.java).stub {
            on { annotations } doReturn sequenceOf(ksAnnotation)
            on { toString() } doReturn funName
        }

        return ksFunctionMock
    }

}